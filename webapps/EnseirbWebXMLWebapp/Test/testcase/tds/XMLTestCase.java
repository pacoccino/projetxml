Compiled from "XMLTestCase.java"
public class fr.enseirb.webxml.junit.testcase.tds.XMLTestCase extends fr.enseirb.webxml.junit.testcase.util.GenericTestCase {
  public fr.enseirb.webxml.junit.testcase.tds.XMLTestCase();
    Code:
       0: aload_0       
       1: invokespecial #8                  // Method fr/enseirb/webxml/junit/testcase/util/GenericTestCase."<init>":()V
       4: return        

  public void testTaskList() throws java.lang.Exception;
    Code:
       0: ldc           #18                 // String /task/list/xml
       2: ldc           #20                 // String data/common/task_list.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: return        

  public void testUserList() throws java.lang.Exception;
    Code:
       0: ldc           #29                 // String /user/list/xml
       2: ldc           #31                 // String data/common/user_list.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: return        

  public void testTaskId1() throws java.lang.Exception;
    Code:
       0: ldc           #34                 // String /task/list/xml?id=1
       2: ldc           #36                 // String data/tds/expected_results/task_id1.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: return        

  public void testTaskId2() throws java.lang.Exception;
    Code:
       0: ldc           #39                 // String /task/list/xml?id=2
       2: ldc           #41                 // String data/tds/expected_results/task_id2.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: return        

  public void testStats() throws java.lang.Exception;
    Code:
       0: ldc           #44                 // String /stats/xml
       2: ldc           #46                 // String data/tds/expected_results/stats.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: return        
}
