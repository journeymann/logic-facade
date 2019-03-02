# logic-digest (general-java-utils)

* This is a general purpose respository that is intended to house general java utility classes that do not belong to a specific project.
* These are Java classes that are intended to showcase common low level bit manipulation operations in Java (Java 1.8).

# Java Classes
#com.research.digest.control.DataScrub.java
 * The program accepts a text file as input then performs a random bitwise overlay of each character in the file. 
 * Specific overlays are defined for alphabet characters as compared to integer digits. non alphanumeric values remain unchanged.
 * The overall effect is that the output file looks very similar in structure to the input file but the characters are all different. 
 
 * This class and it operation may be a very useful tool for scrubbing personal identifiable information (PII)
 * data and data files for use in functional test verification and quality assurance testing situations.  

#com.research.digest.document.adobe.PDFCustomBuilder.java

#com.research.digest.document.adobe.PDFMakerUtil.java

#com.research.digest.document.office.Common.java

#com.research.digest.document.office.ExcelProcess.java

#com.research.digest.document.office.WordProcess.java

#com.research.digest.document.office.Tuple.java

#com.research.digest.validate.Validator.java

#com.research.digest.validate.RegexMatches.java

#com.research.heuristics.Heuristics.java.

#com.research.types.queue.Queue.java

#com.research.types.trees.bplus.classic.Tree.java

#com.research.types.trees.bplus.modern.BPTree.java

* Emphasis placed on code quality by the use of JUnit Test cases
* Designed and configured feature verificatiion as well and regression test 
* cases by the use of JUnit 

#com.research.types.trees.bplus.modern.test.BPTreeTestSuite.java
#com.research.types.trees.bplus.modern.test.BPTreeUnitTest1.java
#com.research.types.trees.bplus.modern.test.BPTreeUnitTest2.java
#com.research.types.trees.bplus.modern.test.UnitTestRunner.java

#com.research.types.trees.iterative.BinarySearchTrees.java
#com.research.types.trees.recursive.BinarySearchTrees.java

 * General document authoring tools used to solve common everyday problems associated with creating/editing microsoft documents 
 * and spreasheets and converting between the various microsoft and adobe documents formats.

# API Libraries
 * itextpdf version 5.5.10
 * poi version 3.15
 * xmlbeans version 2.6.0
 * junit version 4
