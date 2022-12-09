-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2022 at 10:37 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doubtcart`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `Id` int(11) NOT NULL,
  `Username` varchar(500) NOT NULL,
  `Description` text NOT NULL,
  `DoubtID` int(11) NOT NULL,
  `IsHelpful` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`Id`, `Username`, `Description`, `DoubtID`, `IsHelpful`) VALUES
(14, 'admin', '<p><span style=\"color: rgb(35, 38, 41);\">the&nbsp;</span><a href=\"https://stackoverflow.com/a/10013070\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"background-color: rgb(255, 255, 255); color: var(--theme-link-color);\">cursor pattern</a><span style=\"color: rgb(35, 38, 41);\">&nbsp;is not going to fetch all data at once, hence it is returning in only 200ms. to fetch 500k rows is going to take a while no matter what approach you take on the client, as this will require a lot of work for the database server.</span></p>', 5, 1),
(15, 'admin', '<p>new answer</p>', 5, 1),
(17, 'manisha', '<p>Can\'t you just make a sweep and save the min and max?</p><pre class=\"ql-syntax\" spellcheck=\"false\">maximum = -sys.maxsize - 1\r\nminimum = sys.maxsize\r\n\r\nfor elem in arr:\r\n   if elem &lt; minimum:\r\n      minimum = elem\r\n   elif elem &gt; maximum:\r\n      maximum = elem\r\n</pre><p>Would this suit your purposes?</p>', 11, 1),
(18, 'manisha', '<p>The condition in the for loop</p><pre class=\"ql-syntax\" spellcheck=\"false\">for(int i = front; i &lt;= rear; i ++){\r\n    printf(\"%d \", queue[i]);\r\n}\r\n</pre><p>is incorrect. At least you have to write</p><pre class=\"ql-syntax\" spellcheck=\"false\">for(int i = front; i &lt; rear; i ++){\r\n    printf(\"%d \", queue[i]);\r\n}\r\n</pre><p>Also this statement</p><pre class=\"ql-syntax\" spellcheck=\"false\">size+=1\r\n</pre><p>does not make a sense because the size of the dynamically allocated array is fixed.</p><p>And the condition in this if statement</p><pre class=\"ql-syntax\" spellcheck=\"false\">case 1:{\r\n    if(rear == size - 1){\r\n        printf(\"Overflow!\\n\");\r\n    }\r\n</pre><p>is also incorrect. You should write</p><pre class=\"ql-syntax\" spellcheck=\"false\">case 1:{\r\n    if(rear == size){\r\n        printf(\"Overflow!\\n\");\r\n    }\r\n</pre><p>Pay attention to that you should make a circular queue. To do so you should introduce one more variable something like&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">full</code>&nbsp;that is set when&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">rear</code>&nbsp;is equal to&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">front</code>&nbsp;and queue is not empty.</p><p>And that is more important it is better to introduce a structure that describes the queue.</p><p>Here is a demonstration program that shows how it can be done.</p><pre class=\"ql-syntax\" spellcheck=\"false\">#include &lt;stdio.h&gt;\r\n#include &lt;stdlib.h&gt;\r\n\r\nstruct Queue\r\n{\r\n    int *a;\r\n    size_t size;\r\n    size_t front;\r\n    size_t rear;\r\n    int full;\r\n};\r\n\r\nint init( struct Queue *queue, size_t n )\r\n{\r\n    *queue = ( struct Queue ){ NULL, 0, 0, 0, 0 };\r\n    int success = 0;\r\n\r\n    if (n)\r\n    {\r\n        queue-&gt;a = malloc( n * sizeof( int ) );\r\n\r\n        if (( success = queue-&gt;a != NULL ))\r\n        {\r\n            queue-&gt;size = n;\r\n        }\r\n    }\r\n\r\n    return success;\r\n}\r\n\r\nvoid clear( struct Queue *queue )\r\n{\r\n    free( queue-&gt;a );\r\n    *queue = ( struct Queue ){ NULL, 0, 0, 0, 0 };\r\n}\r\n\r\nint enqueue( struct Queue *queue, int value )\r\n{\r\n    int success = queue-&gt;size != 0 &amp;&amp; !queue-&gt;full;\r\n    if (success)\r\n    {\r\n        queue-&gt;a[queue-&gt;rear++] = value;\r\n        queue-&gt;rear %= queue-&gt;size;\r\n\r\n        queue-&gt;full = queue-&gt;front == queue-&gt;rear;\r\n    }\r\n\r\n    return success;\r\n}\r\n\r\nint dequeue( struct Queue *queue, int *value )\r\n{\r\n    int success = queue-&gt;front != queue-&gt;rear || queue-&gt;full;\r\n\r\n    if (success)\r\n    {\r\n        *value = queue-&gt;a[queue-&gt;front++];\r\n        queue-&gt;front %= queue-&gt;size;\r\n        queue-&gt;full = 0;\r\n    }\r\n\r\n    return success;\r\n}\r\n\r\nvoid display( const struct Queue *queue )\r\n{\r\n    if (queue-&gt;front == queue-&gt;rear &amp;&amp; !queue-&gt;full)\r\n    {\r\n        puts( \"The queue is empty.\" );\r\n    }\r\n    else\r\n    {\r\n        printf( \"The queue is \" );\r\n        size_t current = queue-&gt;front;\r\n        do\r\n        {\r\n            printf( \"%d \", queue-&gt;a[current++] );\r\n            current %= queue-&gt;size;\r\n        } while (current != queue-&gt;rear);\r\n        putchar( \'\\n\' );\r\n    }\r\n}\r\n\r\nint is_full( const struct Queue *queue )\r\n{\r\n    return queue-&gt;full;\r\n}\r\n\r\nint main( void )\r\n{\r\n    struct Queue queue;\r\n\r\n    printf( \"Enter the size of the queue: \" );\r\n    size_t n = 0;\r\n\r\n    if (scanf( \"%zu\", &amp;n ) != 1 || !init( &amp;queue, n ))\r\n    {\r\n        puts( \"Error. Can not reserve memory for the queue.\" );\r\n    }\r\n    else\r\n    {\r\n        enum { Exit = 0, Enqueue = 1, Dequeue = 2, Display = 3 };\r\n\r\n        size_t n = 0;\r\n\r\n        do\r\n        {\r\n            puts( \"Queue Operations\\n\\n\"\r\n                \"Press 1 for Enqueue\\n\"\r\n                \"Press 2 for Dequeue\\n\"\r\n                \"Press 3 for Display\\n\"\r\n                \"Press 0 for Exit\\n\" );\r\n\r\n            printf( \"Your choice: \" );\r\n\r\n            if (scanf( \"%zu\", &amp;n ) != 1) break;\r\n\r\n            switch (n)\r\n            {\r\n            case Exit:\r\n            {\r\n                break;\r\n            }\r\n\r\n            case Enqueue:\r\n            {\r\n                if (is_full( &amp;queue ))\r\n                {\r\n                    puts( \"\\nThe queue is full. You can not add a new value.\" );\r\n                }\r\n                else\r\n                {\r\n                    printf( \"\\nEnter the element you want to enqueue : \" );\r\n                    int value;\r\n\r\n                    if (scanf( \"%d\", &amp;value ) != 1)\r\n                    {\r\n                        puts( \"You interrupted the input\" );\r\n                    }\r\n                    else\r\n                    {\r\n                        enqueue( &amp;queue, value );\r\n                        printf( \"The value %d is added to the queue.\\n\", value );\r\n                    }\r\n                }\r\n\r\n                break;\r\n            }\r\n\r\n            case Dequeue:\r\n            {\r\n                int value;\r\n                if (!dequeue( &amp;queue, &amp;value ))\r\n                {\r\n                    puts( \"Error. The queue is empty.\" );\r\n                }\r\n                else \r\n                {\r\n                    printf( \"\\nThe removed element is %d\\n\", value );\r\n                }\r\n                break;\r\n            }\r\n\r\n            case Display:\r\n            {\r\n                putchar( \'\\n\' );\r\n                display( &amp;queue );\r\n                break;\r\n            }\r\n\r\n            default:\r\n            {\r\n                puts( \"Invalid input.\" );\r\n                break;\r\n            }\r\n            }\r\n\r\n            if (n == 0) break;\r\n\r\n            putchar( \'\\n\' );\r\n\r\n        } while (n != 0);\r\n\r\n        clear( &amp;queue );\r\n    }\r\n}\r\n</pre><p>Enjoy!:)</p>', 10, 0),
(19, 'manisha', '<p>I leave you this example, it may have some issues but I think you can get an idea from it. I think the best way to solve this is by playing with the bracket notations, because I mean no matter if you have an object or an array if you do something like variable[key] you will get the value, and that \"key\" can be the property name of an object or an index number from the array. So the 1st I would do is to create an array with those keys. Then I will iterate throw the object using those keys and just update the value, and because it is an object and the type of value is by reference, the update will impact the whole object.</p><p>English is not my main language, so I hope you can understand.</p><pre class=\"ql-syntax\" spellcheck=\"false\">var tree = {\r\n  name: \"docs\",\r\n  type: \"dir\",\r\n  full: \"/home/docs\",\r\n  children: [\r\n    {\r\n      name: \"folder2\",\r\n      type: \"dir\",\r\n      full: \"/home/docs/folder2\",\r\n      children: [\r\n        {\r\n          name: \"file2.txt\",\r\n          type: \"file\",\r\n          full: \"/home/docs/folder2/file2.txt\",\r\n        },\r\n      ],\r\n    },\r\n    {\r\n      name: \"file1.txt\",\r\n      type: \"file\",\r\n      full: \"/home/docs/file1.txt\",\r\n    },\r\n  ],\r\n};\r\n\r\nfunction isObject(obj) {\r\n  return Object.prototype.toString.call(obj) === \"[object Object]\";\r\n}\r\n\r\nfunction isArray(arr) {\r\n  return Array.isArray(arr);\r\n}\r\n\r\nfunction findObjectKeyPathToUpdate(fullPath, treeObj, keys = []) {\r\n  if (isObject(treeObj)) {\r\n    if (\"full\" in treeObj &amp;&amp; treeObj.full === fullPath) {\r\n      return keys;\r\n    } else {\r\n      const currentObjectKeys = Object.keys(treeObj);\r\n      for (let i = 0; i &lt; currentObjectKeys.length; i++) {\r\n        const key = currentObjectKeys[i];\r\n        if (isObject(treeObj[key]) || isArray(treeObj[key])) {\r\n          keys.push(key);\r\n          const res = findObjectKeyPathToUpdate(fullPath, treeObj[key], keys);\r\n          if (res) {\r\n            return res;\r\n          } else {\r\n            keys.pop();\r\n          }\r\n        }\r\n      }\r\n    }\r\n  } else if (isArray(treeObj)) {\r\n    for (let i = 0; i &lt; treeObj.length; i++) {\r\n      const currentObj = treeObj[i];\r\n      if (isObject(currentObj) || isArray(currentObj)) {\r\n        keys.push(i);\r\n        const res = findObjectKeyPathToUpdate(fullPath, currentObj, keys);\r\n        if (res) {\r\n          return res;\r\n        } else {\r\n          keys.pop();\r\n        }\r\n      }\r\n    }\r\n  }\r\n  return null;\r\n}\r\n\r\nfunction updateObjectByKeyPathArr(keyPathArr, obj, newKey, value) {\r\n  for (let i = 0; i &lt; keyPathArr.length; i++) {\r\n    const key = keyPathArr[i];\r\n    obj = obj[key];\r\n    if (i === keyPathArr.length - 1) {\r\n      Object.assign(obj, { [newKey]: value });\r\n    }\r\n  }\r\n}\r\nconst objKeyPathArr = findObjectKeyPathToUpdate(\r\n  \"/home/docs/folder2/file2.txt\",\r\n  tree\r\n);\r\n\r\nupdateObjectByKeyPathArr(objKeyPathArr, tree, \"newKey\", {\r\n  test: \"test\",\r\n});\r\n\r\nconsole.log(\"new\", JSON.stringify(tree));\r\n</pre><p><br></p>', 9, 1),
(21, 'poorvi', '<p>ok</p>', 6, 0),
(22, 'poorvi', '<p>Package.json is under your control and you need to edit it to fit your dependencies.</p><p>So if you would like to remove dependencies this is where you start.</p><p>remove them from package.json and run&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">npm prune</code>.</p><p>you could also just remove node_modules and rerun&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">npm install</code></p>', 12, 0),
(23, 'poorvi', '<p><span style=\"color: rgb(35, 38, 41);\">noticed that the app looks fine in incognito mode on chrome also. I changed the chrome profile to a profile that I don\'t use often, and the app looks fine on that as well. So, it seems like the problem is with the chrome profile that I use generally. I have also tried disabling the extensions that I have, but it\'s still not rendering properly.</span></p>', 13, 0),
(24, 'poorvi', '<ol><li><code style=\"color: var(--black-800); background-color: var(--black-075);\">$unwind</code>&nbsp;- Deconstruct&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">products</code>&nbsp;array to multiple documents.</li><li><code style=\"color: var(--black-800); background-color: var(--black-075);\">$group</code>&nbsp;- Group by&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">products.produtId</code>&nbsp;and sum for&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">products.quantity</code>&nbsp;and&nbsp;<code style=\"color: var(--black-800); background-color: var(--black-075);\">products.total</code>.</li><li><code style=\"color: var(--black-800); background-color: var(--black-075);\">$project</code>&nbsp;- Decorate output documents.</li></ol><pre class=\"ql-syntax\" spellcheck=\"false\">db.collection.aggregate([\r\n  {\r\n    $unwind: \"$products\"\r\n  },\r\n  {\r\n    $group: {\r\n      _id: \"$products.productId\",\r\n      quantity: {\r\n        $sum: \"$products.quantity\"\r\n      },\r\n      total: {\r\n        $sum: \"$products.total\"\r\n      }\r\n    }\r\n  },\r\n  {\r\n    \"$project\": {\r\n      _id: 0,\r\n      productId: \"$_id\",\r\n      quantity: 1,\r\n      total: 1\r\n    }\r\n  }\r\n])\r\n</pre><p><br></p>', 6, 1),
(25, 'admin', '<p>we add to wildfly on section subsystem xmlns=\"urn:jboss:domain:ejb3:2.0\"</p><p>in-vm-remote-interface-invocation pass-by-value=\"false\"</p><p>top level deployment with a META-INF/jboss-ejb-client.xml</p><p>ejb-receivers local-receiver-pass-by-value=\"false\"</p><p><a href=\"https://stackoverflow.com/questions/tagged/java\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-tag-color);\">java</a></p><p><a href=\"https://stackoverflow.com/questions/tagged/ejb-3.0\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-tag-color);\">ejb-3.0</a></p><p><a href=\"https://stackoverflow.com/questions/tagged/wildfly-8\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-tag-color);\">wildfly-8</a></p><p><br></p>', 8, 1),
(26, 'admin', '<p>hey</p>', 13, 0),
(27, 'admin', '<p><img src=\"data:image/jpeg;base64,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\">hehe answer</p>', 5, 0),
(28, 'admin', '<p><span style=\"color: rgb(35, 38, 41);\">It seemed that a lot of dependencies were incorrect.</span></p><p><span style=\"color: rgb(35, 38, 41);\"><span class=\"ql-cursor\">ï»¿</span></span><img src=\"https://i.stack.imgur.com/NXqjP.png\" alt=\"enter image description here\"></p>', 19, 0);

-- --------------------------------------------------------

--
-- Table structure for table `badge`
--

CREATE TABLE `badge` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL,
  `Image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Id`, `Name`) VALUES
(1, 'Theory'),
(2, 'Pactical'),
(3, 'Exam'),
(4, 'Lab'),
(5, 'Placements'),
(6, 'Salary Package'),
(7, 'Faculty'),
(8, 'Fees'),
(9, 'Events'),
(10, 'ATKT'),
(11, 'Admission'),
(12, 'FrontEnd'),
(13, 'BackEnd'),
(14, 'Database'),
(15, 'Server'),
(16, 'Deployment'),
(17, 'Hosting'),
(18, 'Cloud'),
(19, 'DSA'),
(20, 'Projects'),
(21, 'Competitive Programming'),
(22, 'Resume'),
(23, 'Interview');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `CommentID` int(11) NOT NULL,
  `Comment` varchar(2000) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `cdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`CommentID`, `Comment`, `ResourceID`, `UserID`, `cdate`) VALUES
(19, 'the PDF is Too Good and Quality content', 11, 'admin', '2022-06-17'),
(20, 'Queque and Stack ??', 12, 'manisha', '2022-06-17'),
(21, 'Doublly Linked List ?', 12, 'manisha', '2022-06-17'),
(22, 'this Resource is so good', 13, 'manisha', '2022-06-17'),
(23, 'CN is always theory :(', 14, 'manisha', '2022-06-17'),
(24, 'Spring Boot Resource ??', 24, 'manisha', '2022-06-17'),
(25, 'need more Pdf', 24, 'manisha', '2022-06-17'),
(26, 'so Clear about android now\r\n\r\nthanks', 26, 'manisha', '2022-06-17'),
(27, 'C is C great !!', 28, 'manisha', '2022-06-17'),
(28, 'Communication ??!', 14, 'poorvi', '2022-06-17'),
(29, 'Tamplates need to explain', 13, 'poorvi', '2022-06-17'),
(30, 'Jar file missing can we have ?', 24, 'poorvi', '2022-06-17'),
(32, 'hey hahaha', 11, 'poorvi', NULL),
(33, 'hahaha', 25, 'admin', NULL),
(34, 'good contebt', 11, 'admin', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `doubt`
--

CREATE TABLE `doubt` (
  `Id` int(11) NOT NULL,
  `Username` varchar(500) DEFAULT NULL,
  `Title` varchar(1000) NOT NULL,
  `Description` text NOT NULL,
  `Point` int(11) NOT NULL,
  `IsClosed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doubt`
--

INSERT INTO `doubt` (`Id`, `Username`, `Title`, `Description`, `Point`, `IsClosed`) VALUES
(5, 'admin', 'Convert mongoDB AggregationCursor response into array format for 500K+ records is taking lot of time', '<p>Sorry to ask repeated questions, but I referred to too many articles but was not able to solve my use case.</p><p><strong>Technology used</strong></p><ol><li>NodeJs</li><li>mongodb (MongoClient ) librabry with NodeJs</li></ol><p>I have tried two approaches as below:</p><pre class=\"ql-syntax\" spellcheck=\"false\">  const records = client\r\n    .db(mongodbDATABASE)\r\n    .collection(\'subscriptions\')\r\n    .aggregate(pipeline)\r\n    .toArray()\r\nconst records = client\r\n  .db(mongodbDATABASE)\r\n  .collection(\'subscriptions\')\r\n  .aggregate(pipeline)\r\n\r\nwhile (await records.hasNext()) {\r\n  const subscription = await records.next()\r\n  //TO DO with subscriptions\r\n}\r\n</pre><p>I run aggregate pipeline query in compass and NoSQL-booster and I\'m getting responses very quick&nbsp;<strong>(200ms) with 500K records</strong>&nbsp;but whenever I\'m trying to convert the aggregate result to array format by using the above methods(.toArray(), .hasNext() ) it\'s almost taking&nbsp;<strong>3-4 minutes</strong>.</p><p>Could you please help on it how I can convert this AggregationCursor to array format without much impacting the performance?</p>', 3, 1),
(6, 'admin', 'MongoDB - Aggregate a sales table from orders', '<p>Assuming there is a collection where each document contains an array of objects called products.</p><p>//Document 1</p><pre class=\"ql-syntax\" spellcheck=\"false\">{\r\n  id: \"62aac8cfb5722d4c628a4a24\";\r\n  products: [\r\n    {\r\n      productId: \"62aac8cfb5722d4c628a4a24\",\r\n      quantity: 1,\r\n      total: 50,\r\n    },\r\n  ],\r\n}\r\n</pre><p>//Document 2</p><pre class=\"ql-syntax\" spellcheck=\"false\">{\r\n  id: \"62aac8cfb5722d4c628a4a24\";\r\n  products: [\r\n    {\r\n      productId: \"62aac8cfb5722d4c628a4a24\",\r\n      quantity: 2,\r\n      total: 100,\r\n    },\r\n    {\r\n      productId: \"65fasd454daer57f2ads4c\",\r\n      quantity: 2,\r\n      total: 100,\r\n    },\r\n  ],\r\n}\r\n</pre><p>//Document 3</p><pre class=\"ql-syntax\" spellcheck=\"false\">{\r\n  id: \"62aac8cfb5722d4c628a4a24\";\r\n  products: [\r\n    {\r\n      productId: \"62aac8cfb5722d4c628a4a24\",\r\n      quantity: 5,\r\n      total: 200,\r\n    },\r\n  ],\r\n}\r\n</pre><p>Now each document contains an array of products sold, for example product with the id \"62aac8cfb5722d4c628a4a24\" appears in multiple orders. what I want to do is use aggregate to return an array of objects called sales. each object in the array has the product Id (unique), sum of quantity from all documents and sum of total from all documents.</p><pre class=\"ql-syntax\" spellcheck=\"false\">[{\r\n    productId:\"62aac8cfb5722d4c628a4a24\"\r\n    quantity:8,\r\n    total:350\r\n},\r\n{\r\n    productId:\"65fasd454daer57f2ads4c\"\r\n    quantity:2,\r\n    total:200\r\n}]\r\n</pre><p><br></p>', 6, 1),
(8, 'manisha', 'EJB remote injection can return object by reference?', '<p>we add to wildfly</p><pre class=\"ql-syntax\" spellcheck=\"false\">&lt;subsystem xmlns=\"urn:jboss:domain:ejb3:1.2\"&gt;\r\n&lt;!-- Disable pass-by-value for in-vm remote interface invocations on EJBs --&gt;\r\n&lt;in-vm-remote-interface-invocation pass-by-value=\"false\"/&gt;\r\n&lt;/subsystem&gt;\r\n</pre><p>top level deployment war</p><pre class=\"ql-syntax\" spellcheck=\"false\">&lt;jboss-ejb-client xmlns=\"urn:jboss:ejb-client:1.0\"&gt;\r\n&lt;client-context&gt;\r\n    &lt;ejb-receivers local-receiver-pass-by-value=\"false\"/&gt;\r\n&lt;/client-context&gt;\r\n</pre><p>Thanks</p>', 4, 1),
(9, 'poorvi', 'Modify/Add A Array Deeply Nested Inside Object', '<p>I have this Data Structure:</p><pre class=\"ql-syntax\" spellcheck=\"false\">var tree = {\n  name: \"docs\",\n  type: \"dir\",\n  full: \"/home/docs\",\n  children: [\n    {\n      name: \"folder2\",\n      type: \"dir\",\n      full: \"/home/docs/folder2\",\n      children: [\n        {\n          name: \"file2.txt\",\n          type: \"file\",\n          full: \"/home/docs/folder2/file2.txt\"\n        }\n      ]\n    },\n    {\n      name: \"file1.txt\",\n      type: \"file\",\n      full: \"/home/docs/file1.txt\"\n    }\n  ]\n}\n</pre><p>This data structure represents a contents of the folder of the User, so it may vary on every user\'s machine.</p><p>1 thing common in this is every element represents either a file or a directory, if it\'s a directory it will have the property&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">type: \"dir\"</code>&nbsp;else it will have&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">type: \"file\"</code>.</p><p>if the element is a directory it will also have a&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">children</code>&nbsp;property which will be a array of such elements.</p><p>every element also has name property like folder/file name &amp; it has a&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">full</code>&nbsp;property which is a unique string, which defines where the folder/file is on the user\'s filesystem.</p><p>I have written this algorithm:</p><pre class=\"ql-syntax\" spellcheck=\"false\">var tree = {\n  name: \"docs\",\n  type: \"dir\",\n  full: \"/home/docs\",\n  children: [\n    {\n      name: \"folder2\",\n      type: \"dir\",\n      full: \"/home/docs/folder2\",\n      children: [\n        {\n          name: \"file2.txt\",\n          type: \"file\",\n          full: \"/home/docs/folder2/file2.txt\"\n        }\n      ]\n    },\n    {\n      name: \"file1.txt\",\n      type: \"file\",\n      full: \"/home/docs/file1.txt\"\n    }\n  ]\n}\n\nfunction FindChildrenInTree(fullPath, treeObj) {\n    if (treeObj.type != \"dir\") { return null; }\n    if (treeObj.children == null) { return null }\n    if (treeObj.full == fullPath) { return treeObj; }\n\n    for (var i = 0; i &lt; treeObj.children.length; i++) {\n        if (treeObj.children[i].full == fullPath) {\n            return treeObj.children[i];\n        } else {\n            var result = FindChildrenInTree(fullPath, treeObj.children[i]);\n            if (result != null) return result;\n        }\n    }\n\n    return null;\n}\n\nconsole.log(FindChildrenInTree(\"/home/docs/folder2\", tree))\n</pre><p>&nbsp;Run code snippet</p><p><br></p>', 4, 1),
(10, 'poorvi', 'Getting garbage number automatically in the array while applying Queue', '<p>I was trying to make a queue program on my own, but when I enqueue an element and then display the queue, I find an extra element already lying around in my queue. Also, I am not able to add the number of elements as I decided from the size of the queue because of the garbage numbers eating up space in my queue. Here\'s the code for it :</p><pre class=\"ql-syntax\" spellcheck=\"false\">#include &lt;stdio.h&gt;\r\n#include &lt;stdbool.h&gt;\r\n#include &lt;stdlib.h&gt;\r\n\r\nvoid enqueue();\r\nvoid dequeue();\r\nvoid display();\r\n\r\nint main(){\r\n    int size;\r\n    printf(\"Enter the size of the queue : \");\r\n    scanf(\"%d\", &amp;size);\r\n    int choice, element;\r\n    int queue[size];\r\n    int front = 0;\r\n    int rear = 0;\r\n    while(true){\r\n        printf(\"Queue Operations\\nPress 1 for Enqueue\\nPress 2 for Dequeue\\nPress 3 for Display\\nPress 4 for exit\\n\");\r\n        scanf(\"%d\", &amp;choice);\r\n\r\n        switch(choice){\r\n            case 1:{\r\n                if(rear == size - 1){\r\n                    printf(\"Overflow!\\n\");\r\n                }\r\n                else{\r\n                printf(\"Enter the element you want to enqueue : \");\r\n                scanf(\"%d\", &amp;element);\r\n                queue[rear] = element;\r\n                rear ++;\r\n                }\r\n                break;\r\n            }\r\n            case 2:{\r\n                if(front == rear){\r\n                    printf(\"Underflow!\\n\");\r\n                }\r\n                else{\r\n                    printf(\"The element deleted is %d\\n\", queue[front]);\r\n                    front += 1;\r\n                    size+=1;\r\n                }\r\n                break;\r\n            }\r\n            case 3:{\r\n                if(front == rear){\r\n                    printf(\"Queue is empty.\\n\");\r\n                }\r\n                else{\r\n                for(int i = front; i &lt;= rear; i ++){\r\n                    printf(\"%d \", queue[i]);\r\n                }\r\n                printf(\"\\n\");\r\n                }\r\n                break;\r\n            }\r\n            case 4:{\r\n                exit(0);\r\n            }\r\n            default:{\r\n                printf(\"Invalid Choice.\\nTry again.\\n\");\r\n                break;\r\n            }\r\n        }\r\n    }\r\n    return 0;\r\n}\r\n</pre><p><br></p>', 6, 0),
(11, 'poorvi', 'What best time complexity to find maximum and minimum element in an array at the same time?', '<p>I am new to this platform and not sure is this question been asked before or not but I find this quite interesting that what would be the best way to find the Largest and smallest element in array, is it O(2N) N+N for each iteration or performing quick or merge sort to sort the array and access the last index of array and very first index of array.</p><p>I think Performing Quick or Merge sort is better because its&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">(N*Log(N))</code>&nbsp;and both smallest and largest will be able to get at once. And it\'s because&nbsp;<code style=\"background-color: var(--black-075); color: var(--black-800);\">2N &gt; (n*Log(N)</code>.</p><p>Am I thinking it right or not just need confirmation.</p><p>Thank you.</p>', 2, 1),
(12, 'manisha', 'Remove dependency completely from Npm', '<p>I wanna remove some dependencies completely from my project i.e package.json, package-lock.json Package.json</p><pre class=\"ql-syntax\" spellcheck=\"false\">{\n  \"name\": \"application\",\n  \"version\": \"0.0.0\",\n  \"scripts\": {\n    \"ng\": \"ng\",\n    \"start\": \"ng serve\",\n    \"build\": \"ng build\",\n    \"test\": \"ng test\",\n    \"lint\": \"ng lint\",\n    \"e2e\": \"ng e2e\"\n  },\n  \"private\": true,\n  \"dependencies\": {\n    \"@angular-devkit/core\": \"^13.3.7\",\n    \"@angular-devkit/schematics\": \"^13.3.7\",\n    \"@angular/animations\": \"^13.3.11\",\n    \"@angular/cdk\": \"^12.2.13\",\n    \"@angular/common\": \"^13.3.11\",\n    \"@angular/compiler\": \"^13.3.11\",\n    \"@angular/core\": \"^13.3.11\",\n    \"@angular/forms\": \"^13.3.11\",\n    \"@angular/material\": \"^12.2.13\",\n    \"@angular/material-moment-adapter\": \"^12.2.13\",\n    \"@angular/platform-browser\": \"^13.3.11\",\n    \"@angular/platform-browser-dynamic\": \"^13.3.11\",\n    \"@angular/router\": \"^13.3.11\",\n</pre><p>for example I wanna remove @angular/forms so I will give this command</p><pre class=\"ql-syntax\" spellcheck=\"false\">npm uninstall @angular/forms\n</pre><p>But seems its only remove from Node modules How could I remove this completely.</p><p><a href=\"https://stackoverflow.com/questions/tagged/angular\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-tag-color);\">angular</a></p><p><br></p>', 4, 0),
(13, 'manisha', 'Chakra UI app is not being rendered properly on Chrome', '<p>0</p><p><br></p><p><br></p><p><br></p><p>I\'m following a youtube tutorial on a MERN stack project. It uses Chakra UI for the frontend. But I\'ve noticed a weird issue. The app looks perfectly fine on Firefox, but not on Chrome. I have attached the images to show the difference/problem.</p><p>While trying to solve the problem, I noticed that the app looks fine in incognito mode on chrome also. I changed the chrome profile to a profile that I don\'t use often, and the app looks fine on that as well. So, it seems like the problem is with the chrome profile that I use generally. I have also tried disabling the extensions that I have, but it\'s still not rendering properly. I also tried removing the browser cache, but no success. I don\'t want to remove the entire data from my profile. Can you please explain what is causing this issue and what might solve it?</p><p><a href=\"https://i.stack.imgur.com/ROZZ7.png\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-link-color);\">The app looks like this on Chrome</a></p><p><a href=\"https://i.stack.imgur.com/feyeW.png\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: var(--theme-link-color);\">The app looks like this on Firefox</a></p>', 4, 0),
(16, 'admin', 'Error writing to server JAVA', '<p><span style=\"color: rgb(35, 38, 41);\">I am uploading a file from one server to another server using a Java Program \'POST\' method. But I am getting below exception.</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">java.io.IOException: Error writing to server\r\n    at sun.net.www.protocol.http.HttpURLConnection.writeRequests(HttpURLConnection.java:582)\r\n    at sun.net.www.protocol.http.HttpURLConnection.writeRequests(HttpURLConnection.java:594)\r\n    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1216)\r\n    at java.net.HttpURLConnection.getResponseCode(HttpURLConnection.java:379)\r\n    at com.test.rest.HttpURLConnectionExample.TransferFile(HttpURLConnectionExample.java:107)\r\n    at com.test.rest.HttpURLConnectionExample.main(HttpURLConnectionExample.java:44)\r\n</pre><p>I have other method who will authenticate with server. Which will be be called from below code. When I am getting response from server, I am getting above exception. To Transfer a file to server I have written below method. My sample code is below:</p><pre class=\"ql-syntax\" spellcheck=\"false\">public static void TransferFile(){\r\n        String urlStr = \"http://192.168.0.8:8600/audiofile?path=1/622080256/virtualhaircut.mp3\";\r\n        File tempFile = new File(\"/home/MyPath/Workspace/Sample/virtualhaircut.mp3\");\r\n        BufferedWriter br=null;\r\n        HttpURLConnection conn = null;\r\n        URL url;\r\n        try {\r\n            url = new URL(urlStr);\r\n            AuthenticationUser();\r\n            conn = (HttpURLConnection) url.openConnection();\r\n            conn.setDoOutput(true);\r\n            conn.setRequestMethod(\"POST\");\r\n\r\n            conn.setRequestProperty(\"Content-Type\", new MimetypesFileTypeMap().getContentType(tempFile.getName()));\r\n        } catch (MalformedURLException e1) {\r\n            System.out.println(\"Malformed\");\r\n            e1.printStackTrace();\r\n        } catch (ProtocolException e) {\r\n            System.out.println(\"Protocol\");\r\n            e.printStackTrace();\r\n        } catch (IOException e) {\r\n            System.out.println(\"IO\");\r\n            e.printStackTrace();\r\n        }\r\n\r\n        System.out.println(\"line 69\");\r\n\r\n\r\n        FileInputStream fis;\r\n        OutputStream fos;\r\n\r\n\r\n        try {\r\n            System.out.println(\"line 75\");\r\n\r\n                System.out.println(\"line 77\");\r\n                fis = new FileInputStream(tempFile);\r\n                fos = conn.getOutputStream();\r\n                byte[] buf = new byte[1024 * 2];\r\n                int len = 0;\r\n                System.out.println(\"line 80\");\r\n                while ((len = fis.read(buf)) &gt; 0) {\r\n                    fos.write(buf, 0, len);\r\n                    System.out.println(\"line 85\");\r\n                }\r\n                System.out.println(\"line 87\");\r\n                buf = null;\r\n                fos.flush();\r\n                fos.close();\r\n                fis.close();\r\n\r\n        }catch (FileNotFoundException e) {\r\n            System.out.println(\"\");\r\n            e.printStackTrace();\r\n        } catch (IOException e) {\r\n            e.printStackTrace();\r\n        }\r\n        try {\r\n\r\n            if (conn.getResponseCode() == 200) {\r\n                System.out.println(\"here\");\r\n\r\n            }\r\n        } catch (IOException e) {\r\n            e.printStackTrace();\r\n        }\r\n\r\n\r\n    }\r\n</pre><p><br></p>', 2, 0),
(18, 'admin', 'new new new', '<p><img src=\"data:image/jpeg;base64,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\"></p>', 2, 0),
(19, 'admin', 'Errors in pom.xml with dependencies (Missing artifact...) fine fine', '<p><img src=\"https://i.stack.imgur.com/Rn9Wa.png\" alt=\"enter image description here\">Â¿</p>', 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `doubtcategory`
--

CREATE TABLE `doubtcategory` (
  `DoubtID` int(11) NOT NULL,
  `CatID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doubtcategory`
--

INSERT INTO `doubtcategory` (`DoubtID`, `CatID`) VALUES
(5, 1),
(5, 2),
(5, 1),
(5, 3),
(5, 2),
(6, 14),
(5, 1),
(5, 2),
(5, 3),
(8, 2),
(8, 13),
(8, 14),
(8, 2),
(8, 13),
(8, 14),
(8, 15),
(8, 23),
(12, 2),
(12, 16),
(12, 17),
(13, 3),
(13, 12),
(13, 20),
(13, 23),
(9, 1),
(9, 5),
(9, 9),
(9, 14),
(9, 19),
(10, 3),
(10, 13),
(10, 14),
(10, 19),
(10, 20),
(10, 23),
(11, 14),
(11, 18),
(11, 21),
(5, 1),
(5, 2),
(5, 3),
(6, 2),
(6, 3),
(6, 14),
(6, 23),
(8, 2),
(8, 7),
(8, 13),
(8, 14),
(8, 15),
(8, 23),
(16, 2),
(16, 4),
(16, 7),
(16, 15),
(16, 2),
(16, 4),
(16, 7),
(16, 15),
(16, 19),
(19, 3),
(19, 6),
(19, 11),
(19, 3),
(19, 6),
(19, 11),
(19, 3),
(19, 6),
(19, 11),
(18, 3),
(18, 7);

-- --------------------------------------------------------

--
-- Table structure for table `doubtcomment`
--

CREATE TABLE `doubtcomment` (
  `ID` int(11) NOT NULL,
  `Comment` varchar(2000) NOT NULL,
  `DoubtID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `ddate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `doubttags`
--

CREATE TABLE `doubttags` (
  `DoubtID` int(11) NOT NULL,
  `TagID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doubttags`
--

INSERT INTO `doubttags` (`DoubtID`, `TagID`) VALUES
(5, 30),
(5, 25),
(5, 29),
(6, 30),
(5, 25),
(5, 29),
(5, 30),
(8, 3),
(8, 3),
(8, 10),
(8, 14),
(8, 16),
(8, 31),
(12, 12),
(12, 13),
(12, 26),
(12, 28),
(12, 30),
(13, 3),
(13, 15),
(13, 16),
(13, 19),
(9, 16),
(9, 19),
(9, 29),
(9, 30),
(10, 1),
(10, 7),
(10, 12),
(10, 14),
(10, 15),
(10, 29),
(11, 11),
(11, 12),
(11, 15),
(11, 18),
(5, 16),
(5, 19),
(5, 25),
(5, 29),
(5, 30),
(6, 15),
(6, 29),
(6, 30),
(6, 32),
(8, 3),
(8, 10),
(8, 14),
(8, 16),
(8, 31),
(16, 19),
(16, 31),
(16, 19),
(16, 31),
(19, 3),
(19, 8),
(19, 9),
(19, 13),
(19, 21),
(19, 3),
(19, 8),
(19, 9),
(19, 13),
(19, 21),
(19, 3),
(19, 8),
(19, 9),
(19, 13),
(19, 21),
(18, 11),
(18, 12);

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `groupname` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`groupname`) VALUES
('Admin'),
('Moderator'),
('User');

-- --------------------------------------------------------

--
-- Table structure for table `liketb`
--

CREATE TABLE `liketb` (
  `LikeID` int(11) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `lDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

CREATE TABLE `resource` (
  `ResourceID` int(11) NOT NULL,
  `Title` varchar(2000) NOT NULL,
  `Description` varchar(4000) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Subject` varchar(1000) NOT NULL,
  `Image` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `resource`
--

INSERT INTO `resource` (`ResourceID`, `Title`, `Description`, `Semester`, `Subject`, `Image`) VALUES
(11, 'Linked List', 'Linked lists are a common alternative to arrays in the implementation of\r\ndata structures. Each item in a linked list contains a data element of some\r\ntype and a pointer to the next item in the list. It is easy to insert and delete\r\nelements in a linked list, which are not natural operations on arrays, since\r\narrays have a fixed size. On the other hand access to an element in the\r\nmiddle of the list is usually O(n), where n is the length of the list.\r\nAn item in a linked list consists of a struct containing the data element\r\nand a pointer to another linked list. In C0 we have to commit to the type\r\nof element that is stored in the linked list. We will refer to this data as\r\nhaving type elem, with the expectation that there will be a type definition\r\nelsewhere telling C0 what elem is supposed to be. Keeping this in mind\r\nensures that none of the code actually depends on what type is chosen.\r\nThese considerations give rise to the following definition:', 3, 'DSA2', '716135693705_resource.png'),
(12, 'Queues with Linked Lists', 'queue is implemented as a struct with a front and back field. The\r\nfront field points to the front of the queue, the back field points to the back\r\nof the queue. We need these two pointers so we can efficiently access both\r\nends of the queue, which is necessary since dequeue (front) and enqueue\r\n(back) access different ends of the list.\r\nIn the array implementation of queues, we kept the back as one greater\r\nthan the index of the last element in the array. In the linked-list implementation\r\nof queues, we use a similar strategy, making sure the back pointer\r\npoints to one element past the end of the queue. Unlike arrays, there must\r\nbe something in memory for the pointer to refer to, so there is always one\r\nextra element at the end of the queue which does not have valid data or\r\nnext pointer. We have indicated this in the diagram by writing X.\r\n\r\nWe call this a header because it doesnât hold any elements of the queue, just\r\npointers to the linked list that really holds them. The type definition allows\r\nus to use queue as a type that represents a pointer to a queue header. We\r\ndefine it this way so we can hide the true implementation of queues from\r\nthe client and just call it an element of type queue.\r\n\r\nWhen does a struct of this type represent a valid queue? In fact, whenever\r\nwe define a new data type representation we should first think about\r\nthe data structure invariants. Making these explicit is important as we\r\nthink about and write the pre- and postconditions for functions that implement\r\nthe interface.\r\nWhat we need here is if we follow front and then move down the\r\nlinked list we eventually arrive at back. We call this a list segment. We also\r\nwant both front and back not to be NULL so it conforms to the picture, with\r\none element already allocated even if the q', 4, 'DSA', '904625174050_resource.png'),
(13, 'OOP Resource', 'OOP stands for Object-Oriented Programming.\r\n\r\nProcedural programming is about writing procedures or functions that perform operations on the data, while object-oriented programming is about creating objects that contain both data and functions.\r\n\r\nObject-oriented programming has several advantages over procedural programming:\r\n\r\nOOP is faster and easier to execute\r\nOOP provides a clear structure for the programs\r\nOOP helps to keep the C++ code DRY \"Don\'t Repeat Yourself\", and makes the code easier to maintain, modify and debug\r\nOOP makes it possible to create full reusable applications with less code and shorter development time\r\nTip: The \"Don\'t Repeat Yourself\" (DRY) principle is about reducing the repetition of code. You should extract out the codes that are common for the application, and place them at a single place and reuse them instead of repeating it.\r\n\r\nC++ is an object-oriented programming language.\r\n\r\nEverything in C++ is associated with classes and objects, along with its attributes and methods. For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.\r\n\r\nAttributes and methods are basically variables and functions that belongs to the class. These are often referred to as \"class members\".\r\n\r\nA class is a user-defined data type that we can use in our program, and it works as an object constructor, or a \"blueprint\" for creating objects.', 4, 'OOP', '490334841417_resource.png'),
(14, 'Communication', '1.Message:- It is the information to be communicated.\r\nPopular forms of information include text, pictures,\r\naudio, video etc.\r\n2.Sender:- It is the device which sends the data\r\nmessages. It can be a computer, workstation, telephone\r\nhandset etc.\r\n3.Receiver:- It is the device which receives the data\r\nmessages. It can be a computer, workstation, telephone\r\nhandset etc.\r\n4.Transmission Medium:- It is the physical path\r\nby which a message travels from sender to receiver.\r\nSome examples include twisted-pair wire, coaxial cable,\r\nradio waves etc.\r\n5.Protocol:- It is a set of rules that governs the data\r\ncommunications. It represents an agreement between the\r\ncommunicating devices. Without a protocol, two devices\r\nmay be connected but not communicating.', 4, 'Computer Network', '161937520220_resource.png'),
(18, 'Direction of Data Flow', 'to\r\nthe designated receiver. BogusBus is an example\r\nof simplex communication, where the transmitter\r\nsent information to the remote monitoring location,\r\nbut no information is ever sent back to the water\r\ntank. If all we want to do is send information oneway,\r\nthen simplex is just fine. Most applications,\r\nhowever, demand more:\r\n\r\nWith duplex communication, the flow of information\r\nis bi-directional for each device. Duplex can be\r\nfurther divided into two sub-categories\r\n\r\nHalf-duplex communication may be likened to two\r\ntin cans on the ends of a single taut string: Either\r\ncan may be used to transmit or receive, but not at\r\nthe same time. Full-duplex communication is more\r\nlike a true telephone, where two people can talk at\r\nthe same time and hear one another simultaneously,\r\nthe mouthpiece of one phone transmitting the the\r\nearpiece of the other, and vice versa. Full-duplex is\r\noften facilitated through the use of two separate\r\nchannels or networks, with an individual set of wires\r\nfor each direction of communication. It is sometimes\r\naccomplished by means of multiple-frequency\r\ncarrier waves, especially in radio links, where one\r\nfrequency is reserved for each direction of\r\ncommunication.\r\n\r\n\r\n\r\n', 5, 'Computer Network', '613984886943_resource.png'),
(24, 'Introduction to the EJB 3 Architecture', 'An Introduction to EJB\r\nAround 1996, as Java was becoming bolstered by the emergence of technologies (such as\r\nRMI and JTA) that addressed the needs of large-scale applications, the need arose for a\r\nbusiness component framework that could unify these technologies and incorporate\r\nthem under a standard development model. EJB was born to fill this need. Over the past\r\n10 years, it has evolved to encompass numerous features (while ejecting others) and has\r\nmatured into a robust and standard framework for deploying and executing business\r\ncomponents in a distributed, multiuser environment.\r\nWhat Is EJB?\r\nEJB 3 is defined by JSR 220: Enterprise JavaBeans 3. Whereas the previous versions of\r\nEJB were captured in a single document, this JSR now spans three documents (listed following).\r\nThe first document provides a synthesis of the high-level features of the new\r\nrelease, and focuses on the new simplified development model for building EJB components.\r\nThe last two documents address the technical details of the core enterprise bean\r\nframework and the persistence model, respectively.\r\nâ¢ EJB 3 Simplified API gives a high-level overview of the new EJB 3 development\r\nmodel.\r\nâ¢ EJB Core Contracts and Requirements focuses on session and message-driven\r\nbeans.\r\nâ¢ Java Persistence API addresses entities and the persistence framework.\r\nWhat emerges from these three documents that comprise the EJB 3 spec is both a\r\ncomponent model and a framework.\r\n2 CHAPTER 1 â  INTRODUCTION TO THE EJB 3 ARCHITECTURE\r\nThe EJB Component Model\r\nAs a component model, EJB defines three object types that developers may build and\r\ncustomize, as follows:\r\nâ¢ Session beans perform business service operations and orchestrate transaction\r\nand access control behavior.\r\nâ¢ Message-driven beans (MDBs) are invoked asynchronously in response to\r\nexternal events, through association with a messaging queue or topic.\r\nâ¢ Entities are objects that have unique identities and represent persistent\r\nbusiness data.\r\nSession and message-driven beans are EJBs, and are often referred to collectively as\r\nenterprise beans. In earlier versions of EJB, entities were referred to as entity beans, and\r\nalso fell into this category. In EJB 3, however, entities are managed by a persistence\r\nprovider and not the EJB container, and are no longer considered true enterprise beans.\r\nThe EJB Framework\r\nThe EJB framework provides the supporting environment in which EJB components\r\noperate. This includes container transaction and security services, pooling and caching\r\nof resources, component life cycle services, concurrency support, and moreâall of which\r\nwe will explore throughout this book. EJB components specify the details of how they\r\nwish to interact with their supporting container using EJB-specific metadata that is either\r\ncaptured by the container and applied to the EJBâs behavior at run time, or interpreted\r\nat the time an EJB component is deployed to an EJB container and used to construct\r\nwrapping.', 5, 'EJB', '944899391723_resource.png'),
(25, 'EJB 3 Session Beans', 'Introduction to Session Beans\r\nSession beans are Java components that run in either stand-alone EJB containers or EJB\r\ncontainers that are part of standard Java Platform, Enterprise Edition (Java EE) application\r\nservers. These Java components are typically used to model a particular user task or\r\nuse case, such as entering customer information or implementing a process that maintains\r\na conversation state with a client application. Session beans can hold the business\r\nlogic for many types of applications, such as human resources, order entry, and expense\r\nreporting applications.\r\n27\r\nC H A P T E R 2\r\nTypes of Session Beans\r\nSession beans are of two types, as follows:\r\n Stateless: This type of bean does not maintain any conversational state on behalf of\r\na client application.\r\n Stateful: This type of bean maintains state, and a particular instance of the bean is\r\nassociated with a specific client request. Stateful beans can be seen as extensions\r\nto client programs that are running on the server.\r\nWe will drill down into more specifics of stateless and stateful beans in the following\r\nsections.', 8, 'EJB', '593075366355_resource.png'),
(26, 'Introduction to android', 'Android is an open-source operating system based on Linux with a Java programming interface for mobile devices such as Smartphone (Touch Screen Devices who supports Android OS) as well for Tablets too. \r\n\r\n \r\n\r\nAndroid was developed by the Open Handset Alliance (OHA), which is led by Google. The Open Handset Alliance (OHA) is a consortium of multiple companies like Samsung, Sony, Intel and many more to provide services and deploy handsets using the android platform.\r\n\r\n \r\n\r\nIn 2007, Google released a first beta version of the Android Software Development Kit (SDK) and the first commercial version of Android 1.0 (with name Alpha), was released in September 2008.\r\n\r\n \r\n\r\nIn 2012, Google released another version of android, 4.1 Jelly Bean. ItÃÂÃÂ¢ÃÂÃÂÃÂÃÂs an incremental update and it improved a lot in terms of the user interface, functionality, and performance.\r\n\r\n \r\n\r\nIn 2014, Google announced another Latest Version, 5.0 Lollipop. In Lollipop version Google completely revamped the UI by using Material Designs, which is good for the User Interface as well for the themes related. \r\n\r\n \r\n\r\nAll the source code for Android is available free on Git-Hub, Stack overflow, and many more websites. Google publishes most of the code under the Apache License version 2.0.', 7, 'Android', '438209603384_resource.png'),
(28, 'c language', 'C is a general-purpose high level language that was originally developed by Dennis Ritchie for the Unix operating system. It was first implemented on the Digital Eqquipment Corporation PDP-11 computer in 1972.\r\n\r\nThe Unix operating system and virtually all Unix applications are written in the C language. C has now become a widely used professional language for various reasons.', 1, 'c', '660818211556_resource.png'),
(31, 'powerbi', 'Get self-service analytics at enterprise scale\r\nReduce the added cost, complexity, and security risks of multiple solutions with an analytics platform that scales from individuals to the organisation as a whole.\r\n\r\nUse smart tools for strong results\r\nFind and share meaningful insights with hundreds of data visualisations, built-in AI capabilities, tight Excel integration and pre-built and custom data connectors.\r\n\r\nHelp protect your analytics data\r\nKeep your data secure with industry-leading data security capabilities including sensitivity labeling, end-to-end encryption, and real-time access monitoring.', 9, 'BI', '915908350996_resource.png'),
(32, 'test', 'testDS', 8, 'subTest', '915908350996_resource.png');

-- --------------------------------------------------------

--
-- Table structure for table `resource_files`
--

CREATE TABLE `resource_files` (
  `Id` int(11) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `URL` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `resource_files`
--

INSERT INTO `resource_files` (`Id`, `ResourceID`, `URL`) VALUES
(3, 25, '815947546176_resource_files.sql'),
(6, 28, '809807076198_resource_files.pdf'),
(7, 11, '648831826191_resource_files.pdf'),
(8, 12, '842008206740_resource_files.pdf'),
(9, 13, '601346261299_resource_files.pdf'),
(10, 18, '624793724380_resource_files.pdf'),
(11, 24, '884653424503_resource_files.pdf'),
(12, 24, '199377222055_resource_files.pdf'),
(14, 31, '195095817904_resource_files.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`Id`, `Name`) VALUES
(1, 'Urgent'),
(2, 'Exam'),
(3, 'Doubt'),
(4, 'Queue'),
(5, 'Stack'),
(6, 'Pointer'),
(7, 'DSA'),
(8, 'C++ Tamplate'),
(9, 'Generics'),
(10, 'Thread'),
(11, 'Datatypes'),
(12, 'Array'),
(13, 'FileIO'),
(14, 'String'),
(15, 'Loop'),
(16, 'Error'),
(17, 'Algorithm'),
(18, 'SwitchCase'),
(19, 'OOP'),
(20, 'Inheritance'),
(21, 'Polimorphism'),
(22, 'AbstractClass'),
(23, 'Encapsulation'),
(24, 'FriendClassORFunction'),
(25, 'Constructor'),
(26, 'Destructor'),
(27, 'Overloading'),
(28, 'Overriding'),
(29, 'Javascript'),
(30, 'Frontend'),
(31, 'Backend'),
(32, 'SQL');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Username` varchar(500) NOT NULL,
  `Email` varchar(2000) NOT NULL,
  `Password` varchar(5000) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Profile` varchar(1000) DEFAULT NULL,
  `OTP` int(11) NOT NULL,
  `Points` int(11) NOT NULL DEFAULT 0,
  `warnings` int(11) NOT NULL DEFAULT 0,
  `isBlocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Email`, `Password`, `Semester`, `Profile`, `OTP`, `Points`, `warnings`, `isBlocked`) VALUES
('admin', 'admin@gmail.com', 'PBKDF2WithHmacSHA256:2048:+KVuYBPtymjZsMxi1GQoNmtSGZ1cJ+aeazsBRTEgqEM=:BUFV6TN5BRDU7oWR0275w/1e5aZXbZsR0plvfKXTprY=', 0, '829723460601admin.jpg', 0, 25, 0, 0),
('doubtcart', 'doubt.cart@gmail.com', 'PBKDF2WithHmacSHA256:2048:xgSMrfFaEcCv435Z3vrU2L/IJ7m0gGkccvYr4sT6nhQ=:HSVU9y5WryvXuBKG7vDlvv5StJMyegRi5PYDcjoyn/E=', 10, NULL, 0, 0, 0, 0),
('ironman', 'ironman@gmail.com', 'PBKDF2WithHmacSHA256:2048:JM+4+5H6hs2Ecua0h0pFkAflEMhXC1fhICaFxd2ZWAE=:2ypecLBoyAT9ihf8eC/0Zx5tHl1oo+ja9q5EtYy+KG8=', 3, NULL, 0, 0, 0, 0),
('manisha', 'mmgami2000@gmail.com', 'PBKDF2WithHmacSHA256:2048:RaJuxdUhWZlVzxELgaBPREbgLH7z3Fv9MGv5efExBd0=:9pPkfrv0upiOkQgTogRblwMuX9MuJQ1QZiw3B7bynCA=', 9, 'abc', 0, 10, 0, 0),
('poorvi', 'badkaspoorvi@gmail.com', 'PBKDF2WithHmacSHA256:2048:iDcpSMF9eWxFC0Wr3yEZQYPSgJhWZ6sqW/jVAT2A/qo=:r+OC6UzRf6yyAlBdKw79jhATJ8y8abzc1jfbcYqLx0c=', 3, '518918236940poorvi.jpg', 821250, 6, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `userbadge`
--

CREATE TABLE `userbadge` (
  `Username` varchar(500) NOT NULL,
  `BadgeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_groups`
--

CREATE TABLE `user_groups` (
  `groupname` varchar(50) NOT NULL,
  `Username` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_groups`
--

INSERT INTO `user_groups` (`groupname`, `Username`) VALUES
('Admin', 'admin'),
('User', 'manisha'),
('User', 'poorvi'),
('User', 'ironman'),
('User', 'doubtcart');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `adoubt` (`DoubtID`),
  ADD KEY `auser` (`Username`);

--
-- Indexes for table `badge`
--
ALTER TABLE `badge`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`CommentID`),
  ADD KEY `comr` (`ResourceID`),
  ADD KEY `comu` (`UserID`);

--
-- Indexes for table `doubt`
--
ALTER TABLE `doubt`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `dusernm` (`Username`);

--
-- Indexes for table `doubtcategory`
--
ALTER TABLE `doubtcategory`
  ADD KEY `cat` (`CatID`),
  ADD KEY `doubtc` (`DoubtID`);

--
-- Indexes for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `cdoubt` (`DoubtID`),
  ADD KEY `cduser` (`UserID`);

--
-- Indexes for table `doubttags`
--
ALTER TABLE `doubttags`
  ADD KEY `tag` (`TagID`),
  ADD KEY `tdoubt` (`DoubtID`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`groupname`);

--
-- Indexes for table `liketb`
--
ALTER TABLE `liketb`
  ADD PRIMARY KEY (`LikeID`),
  ADD KEY `liker` (`ResourceID`),
  ADD KEY `likeu` (`UserID`);

--
-- Indexes for table `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`ResourceID`);

--
-- Indexes for table `resource_files`
--
ALTER TABLE `resource_files`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `resfile` (`ResourceID`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `userbadge`
--
ALTER TABLE `userbadge`
  ADD KEY `badge` (`BadgeID`),
  ADD KEY `buser` (`Username`);

--
-- Indexes for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD KEY `guser` (`Username`),
  ADD KEY `groups` (`groupname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `badge`
--
ALTER TABLE `badge`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `doubt`
--
ALTER TABLE `doubt`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `liketb`
--
ALTER TABLE `liketb`
  MODIFY `LikeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `resource`
--
ALTER TABLE `resource`
  MODIFY `ResourceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `resource_files`
--
ALTER TABLE `resource_files`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `adoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`),
  ADD CONSTRAINT `auser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comr` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`),
  ADD CONSTRAINT `comu` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubt`
--
ALTER TABLE `doubt`
  ADD CONSTRAINT `dusernm` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubtcategory`
--
ALTER TABLE `doubtcategory`
  ADD CONSTRAINT `cat` FOREIGN KEY (`CatID`) REFERENCES `category` (`Id`),
  ADD CONSTRAINT `doubtc` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`);

--
-- Constraints for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  ADD CONSTRAINT `cdoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`),
  ADD CONSTRAINT `cduser` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubttags`
--
ALTER TABLE `doubttags`
  ADD CONSTRAINT `tag` FOREIGN KEY (`TagID`) REFERENCES `tags` (`Id`),
  ADD CONSTRAINT `tdoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`);

--
-- Constraints for table `liketb`
--
ALTER TABLE `liketb`
  ADD CONSTRAINT `liker` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`),
  ADD CONSTRAINT `likeu` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `resource_files`
--
ALTER TABLE `resource_files`
  ADD CONSTRAINT `resfile` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`);

--
-- Constraints for table `userbadge`
--
ALTER TABLE `userbadge`
  ADD CONSTRAINT `badge` FOREIGN KEY (`BadgeID`) REFERENCES `badge` (`Id`),
  ADD CONSTRAINT `buser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `groups` FOREIGN KEY (`groupname`) REFERENCES `groups` (`groupname`),
  ADD CONSTRAINT `guser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
