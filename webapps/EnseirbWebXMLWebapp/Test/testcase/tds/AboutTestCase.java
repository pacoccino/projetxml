Compiled from "AboutTestCase.java"
public class fr.enseirb.webxml.junit.testcase.tds.AboutTestCase extends fr.enseirb.webxml.junit.testcase.util.GenericTestCase {
  public fr.enseirb.webxml.junit.testcase.tds.AboutTestCase();
    Code:
       0: aload_0       
       1: invokespecial #8                  // Method fr/enseirb/webxml/junit/testcase/util/GenericTestCase."<init>":()V
       4: return        

  public void testPostTeacher() throws java.lang.Exception;
    Code:
       0: iconst_2      
       1: anewarray     #18                 // class java/lang/String
       4: dup           
       5: iconst_0      
       6: ldc           #20                 // String nouveauprof
       8: aastore       
       9: dup           
      10: iconst_1      
      11: ldc           #22                 // String autreprof
      13: aastore       
      14: astore_1      
      15: iconst_0      
      16: istore_2      
      17: goto          60
      20: ldc           #24                 // String about/teacher/post
      22: aload_1       
      23: iload_2       
      24: aaload        
      25: invokestatic  #26                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.postContent:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      28: pop           
      29: ldc           #32                 // String about?action=teacher
      31: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      34: astore_3      
      35: aload_3       
      36: ifnull        53
      39: aload_3       
      40: aload_1       
      41: iload_2       
      42: aaload        
      43: invokevirtual #38                 // Method java/lang/String.contains:(Ljava/lang/CharSequence;)Z
      46: ifeq          53
      49: iconst_1      
      50: goto          54
      53: iconst_0      
      54: invokestatic  #42                 // Method assertTrue:(Z)V
      57: iinc          2, 1
      60: iload_2       
      61: aload_1       
      62: arraylength   
      63: if_icmplt     20
      66: return        

  public void testFull() throws java.lang.Exception;
    Code:
       0: ldc           #55                 // String about?action=full
       2: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
       5: astore_1      
       6: ldc           #57                 // String about
       8: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      11: astore_2      
      12: aload_1       
      13: aload_2       
      14: invokestatic  #59                 // Method assertEquals:(Ljava/lang/String;Ljava/lang/String;)V
      17: return        

  public void testNotEmptyNames() throws java.lang.Exception;
    Code:
       0: ldc           #66                 // String about?action=studentsNumber
       2: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
       5: astore_1      
       6: aload_1       
       7: invokestatic  #68                 // Method java/lang/Integer.parseInt:(Ljava/lang/String;)I
      10: istore_2      
      11: goto          21
      14: astore_3      
      15: ldc           #74                 // String Student Number is not an integer
      17: invokestatic  #76                 // Method fail:(Ljava/lang/String;)V
      20: return        
      21: iload_2       
      22: anewarray     #18                 // class java/lang/String
      25: astore_3      
      26: iload_2       
      27: anewarray     #18                 // class java/lang/String
      30: astore        4
      32: iconst_0      
      33: istore        5
      35: goto          142
      38: new           #80                 // class java/lang/StringBuilder
      41: dup           
      42: ldc           #82                 // String about?action=firstName&studentId=
      44: invokespecial #84                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
      47: iload         5
      49: invokevirtual #86                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      52: iconst_1      
      53: invokevirtual #86                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      56: invokevirtual #90                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      59: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      62: astore        6
      64: new           #80                 // class java/lang/StringBuilder
      67: dup           
      68: ldc           #94                 // String about?action=lastName&studentId=
      70: invokespecial #84                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
      73: iload         5
      75: invokevirtual #86                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      78: iconst_1      
      79: invokevirtual #86                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      82: invokevirtual #90                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      85: invokestatic  #34                 // Method fr/enseirb/webxml/junit/testcase/util/TestsToolkit.getContent:(Ljava/lang/String;)Ljava/lang/String;
      88: astore        7
      90: aload         6
      92: invokestatic  #96                 // Method assertNotNull:(Ljava/lang/Object;)V
      95: aload         7
      97: invokestatic  #96                 // Method assertNotNull:(Ljava/lang/Object;)V
     100: aload         6
     102: invokevirtual #100                // Method java/lang/String.isEmpty:()Z
     105: invokestatic  #104                // Method assertFalse:(Z)V
     108: aload         7
     110: invokevirtual #100                // Method java/lang/String.isEmpty:()Z
     113: invokestatic  #104                // Method assertFalse:(Z)V
     116: aload         6
     118: aload         7
     120: invokevirtual #107                // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     123: invokestatic  #104                // Method assertFalse:(Z)V
     126: aload_3       
     127: iload         5
     129: aload         6
     131: aastore       
     132: aload         4
     134: iload         5
     136: aload         7
     138: aastore       
     139: iinc          5, 1
     142: iload         5
     144: iload_2       
     145: if_icmplt     38
     148: iload_2       
     149: iconst_2      
     150: if_icmplt     179
     153: aload_3       
     154: iconst_0      
     155: aaload        
     156: aload_3       
     157: iconst_1      
     158: aaload        
     159: invokevirtual #107                // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     162: invokestatic  #104                // Method assertFalse:(Z)V
     165: aload         4
     167: iconst_0      
     168: aaload        
     169: aload         4
     171: iconst_1      
     172: aaload        
     173: invokevirtual #107                // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     176: invokestatic  #104                // Method assertFalse:(Z)V
     179: return        
    Exception table:
       from    to  target type
           6    11    14   Class java/lang/Exception
}
