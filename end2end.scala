import gov.llnl.gs.escore.ComputeKWScores
import gov.llnl.gs.escore.IndicatorQuery
import gov.llnl.gs.graphetl.Schema
import gov.llnl.gs.graphetl.GraphLoader
import org.apache.spark.storage.StorageLevel
import gov.llnl.gs.escore.KWDiagnostics
import java.io.File

// Load the eScore pubmed data from the local file system
// val csv = "file:///data/raw_data_to_hdfs/pubmed/gold/01_percent/pubmedCentral_001"
val csv = "hdfs://mintaka001-stg.ornl.gov:8020/pubmed/gold/01_percent/pubmedCentral_001"

// Read in the eScore data file
val csvRDD = sc.textFile(csv)
// Record some data
println("Data file line count: " + csvRDD.count) 

// Load the keyword file
val keywords = "hdfs://mintaka001-stg.ornl.gov:8020/pubmed/keywords/keywords_pubmed.txt"
val keywordRDD = sc.textFile(keywords)

// Load the keyword types
val keywordTypes = keywordRDD.map(x => x.split("\t")(1)).collect().distinct

// Load the schema file from HDFS
// e.g. hdfs://pubmed/schema/pmc.json
val schema = Schema.extract("hdfs://mintaka001-stg.ornl.gov/pubmed/schema/pmc.json", sc)

// Create the graph from the delimited data file
val graph1 = GraphLoader.loadGraph(sc, csv, schema, keywords)

// print graph node and edge count
println("graph1 node count: " + graph1.numVertices)
println("graph1 edge count: " + graph1.numEdges)

// save (serialize) the graph to an hdfs file
val graph1_file = "hdfs://mintaka001-stg.ornl.gov:8020/vvuq/test/graph1_out"
GraphLoader.serialize(graph1, graph1_file)

// read (de-serialize) the graph from an hdfs file
val graph2 = GraphLoader.deserialize(sc, graph1_file)
println("graph2 node count: " + graph2.numVertices)
println("graph2 edge count: " + graph2.numEdges)

// save (serialize) the pruned graph to an hdfs file
val graph2_file = "hdfs://mintaka001-stg.ornl.gov:8020/vvuq/test/graph2_out"
GraphLoader.serialize(graph2, graph2_file)

// Graph diagnostics
gov.llnl.gs.escore.KWDiagnostics.nodeDiagnostic(graph2, "fermentation", "KeywordList", "Research_Words_and_Materials")
gov.llnl.gs.escore.KWDiagnostics.indicatorDiagnostic(graph2, "Research_Words_and_Materials")

// Prune the graph
val pruned_graph = gov.llnl.gs.escore.Queries.getPrunedGraph(graph2, "Person", "KeywordList")

// Do the Random Walk with Restart (RWR) computation
// Number of RWR iterations
val num_iters = 4
val rwrScores = gov.llnl.gs.escore.Queries.fastRWRQuery(pruned_graph, num_iters, sc)

// Store the RWR results in a file for later use
val rwr_scores_file = "hdfs://mintaka001-stg.ornl.gov:8020/vvuq/test/rwr_scores_out"
gov.llnl.gs.escore.Queries.saveEScores(rwrScores, rwr_scores_file)

// Retrieve the RWR results from the file
val rwr_scores_from_file = gov.llnl.gs.escore.Queries.loadEScores(sc, rwr_scores_file)

// Compute eScores
val indicator_weights = Map("Dissemination-Equipment" -> 1.0, "Post-Production-Equipment" -> 1.0, "Research_Words_and_Materials" -> 1.0)
val output_types = Set("Person")

// Max number of results
val max_results = 1000

// Generate ranked list
val ranked_entities_list = gov.llnl.gs.escore.Queries.fastRankQuery(pruned_graph, rwr_scores_from_file, indicator_weights, output_types, max_results, aggregator= "sum")

// PRINT THE RESULTS AS PIPE-SEPARATED RECORDS

// the number of influential keywords that are printed on each line of output
val num_influential_keywords = 5

// LOTS OF OUTPUT that is printed to a file in the next call to this same function. Uncommend to dump to stdout

// save the output to a file
println("XXXXX")
// val query_results_file = "hdfs://mintaka001-stg.ornl.gov:8020/vvuq/test/query_results_out"
gov.llnl.gs.escore.Queries.formatAndPrintRecords(pruned_graph, ranked_entities_list, indicator_weights, num_influential_keywords, "/data/end2end/query_results_out")
println("XXXXX")
// gov.llnl.gs.escore.Queries.formatAndPrintRecords(pruned_graph, ranked_entities_list, indicator_weights, num_influential_keywords)


// Query operations

// Print rows containing the term “Metabolism” to screen
gov.llnl.gs.escore.Queries.matchingRows(csvRDD, List("Metabolism"), "/data/end2end/metabolism_file.out")

// Save rows containing the term “drone” OR “uav” to a file
gov.llnl.gs.escore.Queries.matchingRows(csvRDD, List(" drone ", "uav "), "/data/end2end/drone_file.out")

// Analyze a node's connections
gov.llnl.gs.escore.Queries.connections(graph2, "Metabolism")
gov.llnl.gs.escore.Queries.connections(graph2, "Metabolism::Subject::")

// Node neighborhood
gov.llnl.gs.escore.Queries.connectionsHop(graph2, "Metabolism")
gov.llnl.gs.escore.Queries.connections(graph2, "Metabolism::Subject::", "/data/end2end/triplet_file.out")

// Multiple Indicator Query

// Persons connected to one keyword type
gov.llnl.gs.escore.Queries.indicatorQuery(graph2, List( "Production-Equipment"), "Person")

// Persons connected to two keyword types
gov.llnl.gs.escore.Queries.indicatorQuery(graph2, List("Acquisition-Equipment ", "Production-Equipment"), "Person")
gov.llnl.gs.escore.Queries.indicatorQuery(graph2, List("Threat_Agents_and_Precursors", "Acquisition-Equipment"), "Person")

// Entities connected to three keyword types, saved to file
gov.llnl.gs.escore.Queries.indicatorQuery(graph2, List("Threat_Agents_and_Precursors", "Production-Equipment", "Acquisition-Equipment"), "Person", "/data/end2end/three_keywords_file.out")



