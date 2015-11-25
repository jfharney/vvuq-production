var VVUQ = VVUQ || {};

VVUQ.num_listings = 10;


VVUQ.keyword_list = ['keyword1', 'keyword2', 'keyword3', 'keyword4', 'keyword5'];

VVUQ.available_datasets = ['all','pubmedCentral_001','pubmedCentral_01','pubmedCentral_1','pubmedCentral'];

VVUQ.test23LLNLresults = [
  	 { 'name' : 'N/A', 'type' : 'N/A', 'total_score' : 'N/A' }//,
     //{ 'name' : 'LLNLname2', 'type' : 'LLNLtype2', 'total_score' : 'LLNLtotal_score2' },
];
VVUQ.test23HSARPAresults = [
     { 'name' : 'N/A', 'type' : 'N/A', 'total_score' : 'N/A' }//,
     //{ 'name' : 'HSARPAname2', 'type' : 'HSARPAtype2', 'total_score' : 'HSARPAtotal_score2' },
];


VVUQ.ornl_details = 'LSB ';
VVUQ.ornl_details += '\n';
VVUQ.ornl_details += 'Version:	:base-4.0-amd64:base-4.0-noarch:core-4.0-amd64:core-4.0-noarch:gra\n';
VVUQ.ornl_details += 'phics-4.0-amd64:graphics-4.0-noarch:printing-4.0-amd64:printing-4.0-noarch\n';
VVUQ.ornl_details += 'Distributor ID:	CentOS\n';
VVUQ.ornl_details += 'Description:	CentOS release 6.7 (Final)\n';
VVUQ.ornl_details += 'Release:	6.7\n';
VVUQ.ornl_details += 'Codename:	Final\n';
VVUQ.ornl_details += '\n';
VVUQ.ornl_details += 'HW...\n';
VVUQ.ornl_details += '9 nodes in the cluser (DL380 Gen 8)\n';
VVUQ.ornl_details += 'Between 92 and 128 GB each\n';
VVUQ.ornl_details += '15 x 1 TB drives\n';

VVUQ.llnl_details = 'Specs: ';
VVUQ.llnl_details += '\n';
VVUQ.llnl_details += '    OS/Version ';
VVUQ.llnl_details += 'RedHat 6.6 ';
VVUQ.llnl_details += '    Software(s)/Version(s) ';
VVUQ.llnl_details += 'Spark 1.3.0';
VVUQ.llnl_details += 'Mesos 0.21.1';
VVUQ.llnl_details += 'Cloudera  CDH 4.7.1'
VVUQ.llnl_details += '\n';
VVUQ.llnl_details += '    Hardware Specs (informational) ';
VVUQ.llnl_details += '8 worker nodes: 24 core / 256GB RAM / Intel(R) Xeon(R) CPU E5-2630 0 ';
VVUQ.llnl_details += '@ 2.30GHz (12 physical cores/ 24 cores with hyper threading); 12 3TB ';
VVUQ.llnl_details += 'SATA disks ';
VVUQ.llnl_details += '1 master node with the same config ';
VVUQ.llnl_details += '3 zk nodes with 64 GB RAM and the same CPU; 2 3TB SATA disks HDFS is running on the cluster using the zk nodes for HA namenodes and the worker nodes as the data nodes';

VVUQ.hsarpa_details = 'Not Available';




















