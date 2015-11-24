package gov.ornl.vvuq.ext;
//real pacakge is 

public class LLNLMetrics {

	/********************************************************
	    * DEF t21
	    ********************************************************/
	public String t20(String testName){
/*
		      var time: Long = 0l
		      if(testname == "pubmedCentral_001"){
		        var time = TimedFunction.milis(() => runQuery(NAMENODE + "/pubmed/gold/01_percent/pubmedCentral_001"))
		      }else if(testname == "pubmedCentral_01"){
		        var time = TimedFunction.milis(() => runQuery(NAMENODE + "/pubmed/gold/1_percent/pubmedCentral_01"))
		      }else if(testname == "pubmedCentral_10"){
		        var time = TimedFunction.milis(() => runQuery(NAMENODE + "/pubmed/gold/10_percent/pubmedCentral_1"))
		      }else if(testname == "pubmedCentral"){
		        var time = TimedFunction.milis(() => runQuery(NAMENODE + "/pubmed/gold/100_percent/pubmedCentral"))
		      }else{
		        println("OPTIONS ARE: pubmedCentral_01, pubmedCentral_01, pubmedCentral_10, or pubmedCentral")
		      }
		      return time.toString
*/
		return null;
	} // END DEF t20

	
	/********************************************************
	    * DEF t21
	    ********************************************************/
	public String t21(String input_path, String output_path){
/*
	    	val f = javax.xml.parsers.SAXParserFactory.newInstance()
	    		      f.setNamespaceAware(false)
	    		      f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
	    		      f.setFeature("http://xml.org/sax/features/validation", false);
	    		      f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
	    		      f.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	    		      f.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	    		      val MyXML = XML.withSAXParser(f.newSAXParser())
	    		      val root = RecursiveFileIterable.iterate(new File(input_path))
	    		      val out = new PrintWriter(output_path)
	    		      val i = root.iterator()
	    		      while (i.hasNext()) {
	    		        val file = i.next()
	    		        try{
	    		          val xml = MyXML.loadFile(file)
	    		          val journalId = getText(xml \ "front" \ "journal-meta" \ "journal-id")
	    		          val journalTitle = getText(xml \ "front" \ "journal-meta" \ "journal-title-group" \ "journal-title")
	    		          val publisherName = getText(xml \ "front" \ "journal-meta" \ "publisher" \ "publisher-name")
	    		          val issn = getText(xml \ "front" \ "journal-meta" \ "issn")
	    		          val idList = xml \ "front" \ "article-meta" \ "article-id"
	    		          val pmid = getText(idList.filter { node => (node \ "@pub-id-type").text == "pmid" })
	    		          val pmc = getText(idList.filter { node => (node \ "@pub-id-type").text == "pmc" })
	    		          val doi = getText(idList.filter { node => (node \ "@pub-id-type").text == "doi" })
	    		          val publisherId = getText(idList.filter { node => (node \ "@pub-id-type").text == "publisher-id" })
	    		          val title = getText(xml \ "front" \ "article-meta" \ "title-group" \ "article-title")
	    		          val contribs = xml \ "front" \ "article-meta" \ "contrib-group" \ "contrib"
	    		          val names = contribs.map(node => getText(node \ "name" \ "surname") + " " + getText(node \ "name" \ "given-names"))
	    		          val affiliationNodes = xml \ "front" \ "article-meta" \ "aff"
	    		          val affiliations = affiliationNodes.map(node => {
	    		            if (node.child.nonEmpty) {
	    		              val children = node.child
	    		              val notLabel = children.filter { c => c.label != "label" }
	    		              val text = notLabel.filter { c => c.label == "#PCDATA" }
	    		              text.mkString(" ")
	    		            }else{
	    		              ""
	    		            }
	    		          })
	    		          val dates = (xml \ "front" \ "article-meta" \ "pub-date")
	    		          val dateMap = dates.map(d => (getPubType(d), extractDate(d))).toMap
	    		          val date = selectDate(dateMap)
	    		          val pubDate = date
	    		          val subject = getText(xml \ "front" \ "article-meta" \ "article-categories" \ "subj-group" \ "subject")
	    		          val author = names.mkString("|")
	    		          val affiliation = affiliations.mkString("|")
	    		          val journalFullTitle = journalTitle
	    		          val abstractStr = getText(xml \ "front" \ "article-meta" \ "abstract")
	    		          val raw = getText(xml \ "body")
	    		          val data = List(date, pubDate, pmid, pmc, doi, issn, subject, author, affiliation, publisherName, publisherId, journalId, journalTitle, journalFullTitle, title, abstractStr, raw)
	    		          val dataStr = data.map(s => s.replaceAll("\\s+", " "))
	    		          out.println(dataStr.mkString("\t"))
	    		        } catch {
	    		          case ex: Exception => { println(file.getName); println(ex.getMessage()); ex.printStackTrace() }
	    		        }
	    		      } // END WHILE
	    		      out.flush()
	    		      out.close()
*/	    		      
	    	return null;
	} // END DEF t21

	
}
