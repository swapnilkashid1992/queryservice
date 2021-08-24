create DATABASE IF NOT EXISTS queryservice;
USE queryservice;
CREATE TABLE IF NOT EXISTS `query` (

  `id` int(11) NOT NULL auto_increment,   
  `identifier` varchar(250) NOT NULL,       
  `query` varchar(250)  NOT NULL,
  PRIMARY KEY(id)
  );
