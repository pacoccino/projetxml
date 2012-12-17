Compiled from "URLTestCase.java"
public class fr.enseirb.webxml.junit.testcase.tds.URLTestCase extends fr.enseirb.webxml.junit.testcase.util.GenericTestCase {
  public fr.enseirb.webxml.junit.testcase.tds.URLTestCase();
    Code:
       0: aload_0       
       1: invokespecial #8                  // Method fr/enseirb/webxml/junit/testcase/util/GenericTestCase."<init>":()V
       4: return        

  public void testURLTask() throws java.lang.Exception;
    Code:
       0: ldc           #18                 // String /task/list/xml
       2: ldc           #20                 // String data/common/task_list.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: ldc           #28                 // String /task/create/url?title=Nouvelle+action+via+URL&description=Son+ajout+sera+a+confirmer&owner=matLo&asker=matLo&deadline=14/01/2013&priority=4
       9: invokestatic  #30                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      12: pop           
      13: ldc           #18                 // String /task/list/xml
      15: ldc           #34                 // String data/tds/expected_results/task_list_after_url.xml
      17: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
      20: return        

  public void testURLUser() throws java.lang.Exception;
    Code:
       0: ldc           #37                 // String /user/list/xml
       2: ldc           #39                 // String data/common/user_list.xml
       4: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
       7: ldc           #41                 // String /user/create/url?name=urlUser
       9: invokestatic  #30                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      12: pop           
      13: ldc           #37                 // String /user/list/xml
      15: ldc           #43                 // String data/tds/expected_results/user_list_after_url.xml
      17: invokestatic  #22                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.compareXML:(Ljava/lang/String;Ljava/lang/String;)V
      20: return        
}
