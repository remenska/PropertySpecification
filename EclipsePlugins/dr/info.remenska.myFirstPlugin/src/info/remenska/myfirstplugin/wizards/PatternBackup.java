package info.remenska.myfirstplugin.wizards;

import java.util.HashMap;

public class PatternBackup {
  public static HashMap<String,HashMap<String,String>> patterns;
  public static HashMap<String, HashMap<String,Boolean>> patternsMonitorable; 
   public static void addPattern(String scope, String behavior, String formula){
	   HashMap<String,String> behaviors = patterns.get(scope);
	   behaviors.put(behavior, formula);
	   patterns.put(scope,behaviors);
   }
   
   public static void addPatternMonitorable(String scope, String behavior, Boolean isMonitorable){
	   HashMap<String,Boolean> behaviorsMonitorable = patternsMonitorable.get(scope);
	   behaviorsMonitorable.put(behavior, isMonitorable);
	   patternsMonitorable.put(scope,behaviorsMonitorable);

   }
   
   public static void fill(){
	   
	   patterns = new HashMap<String,HashMap<String,String>>();
//		  System.out.println(patterns.keySet());
			  patterns.put("Globally", new HashMap<String, String>());
			  patterns.put("Before R", new HashMap<String, String>());
			  patterns.put("After Q", new HashMap<String, String>());
			  patterns.put("Between Q and R", new HashMap<String, String>());
			  patterns.put("After Q until R", new HashMap<String, String>());
			  patternsMonitorable = new HashMap<String,HashMap<String,Boolean>>();
//			  System.out.println(patterns.keySet());
			  patternsMonitorable.put("Globally", new HashMap<String, Boolean>());
			  patternsMonitorable.put("Before R", new HashMap<String, Boolean>());
			  patternsMonitorable.put("After Q", new HashMap<String, Boolean>());
			  patternsMonitorable.put("Between Q and R", new HashMap<String, Boolean>());
			  patternsMonitorable.put("After Q until R", new HashMap<String, Boolean>());
				  
	   addPattern("Globally", "Absence", "[true*. P] false" );
	   addPattern("Before R", "Absence", "[(! R)*. P. (! R)*. R] false");
	   addPattern("After Q", "Absence", "[(! Q)*. Q. true*. P] false");
	   addPattern("Between Q and R", "Absence", "[true*. Q. (! R)*. P. (! R)*. R] false" );
	   addPattern("After Q until R", "Absence", "[true*. Q. (! R)*. P] false");
	   
	   addPattern("Globally", "Existence","mu X. <true> true && [! P] X" );
	   addPattern("Before R", "Existence", "[(! P)*. R] false");
	   addPattern("After Q", "Existence", "[(! Q)*. Q] mu X. <true> true && [! P] X");
	   addPattern("Between Q and R", "Existence", "[true*. Q. (! (P || R))*. R] false");
	   addPattern("After Q until R", "Existence", "[true*. Q] mu X. <true> true && [R] false && [! P] X");
	   
	   addPattern("Globally", "Universality", "[true*. ! P] false ");
	   addPattern("Before R", "Universality", "[(! R)*. ! (P || R). (! R)*. R] false ");
	   addPattern("After Q", "Universality", "[(! Q)*. Q. true*. ! P] false ");
	   addPattern("Between Q and R", "Universality", "[true*. Q. (! R)*. ! (P || R). (! R)*. R] false");
	   addPattern("After Q until R", "Universality", "[true*. Q. (! R)*. ! (P || R)] false ");
	   
	   addPattern("Globally", "Precedence", "[(! P)*. S] false ");
	   addPattern("Before R" , "Precedence" , "[(! (P || R))*. S. (! R)*. R] false " );
	   addPattern("After Q", "Precedence", "[(! Q)*. Q. (! P)*. S] false ");
	   addPattern("Between Q and R", "Precedence", "[true*. Q. (! (P || R))*. S. (! R)*. R] false");
	   addPattern("After Q until R", "Precedence", "[true*. Q. (! (P || R))*. S] false ");
	   
	   
	   addPattern("Globally", "Response", "[true*. P] mu X. <true> true && [! S] X ");
	   addPattern("Before R", "Response", "[(! R)*. P. (! (S || R))*. R] false ");
	   addPattern("After Q", "Response", "[(! Q)*. Q. true*. P] mu X. <true> true && [! S] X " );
	   addPattern("Between Q and R", "Response", "[true*. Q. (! R)*. P. (! (S || R))*. R] false");
	   addPattern("After Q until R", "Response", "[true*. Q. (! R)*. P] mu X. <true> true && [R] false && [! S] X");
	   
	   addPattern("Globally", "Precedence Chain 1", "[(! S)*. (nil | (S. (! T)*)). P] false " );
	   addPattern("Before R", "Precedence Chain 1", " [(! (S || R))*. (nil | (S. (! (T || R))*)). P. (! R)*. R] false ");
	   addPattern("After Q", "Precedence Chain 1", " [(! Q)*. Q. (! S)*. (nil | (S. (! T)*)). P] false ");
	   addPattern("Between Q and R", "Precedence Chain 1", "[true*. Q. (! (S || R))*. (nil | (S. (! (T || R))*)). P.(! R)*. R] false");
	   addPattern("After Q until R", "Precedence Chain 1", " [true*. Q. (! (S || R))*. (nil | (S. (! (T || R))*)). P] false ");
	  
	   
	   addPattern("Globally", "Precedence Chain 2", " [(! P)*. S. (! T)*. T] false ");
	   addPattern("Before R", "Precedence Chain 2", " [(! (P || R))*. S. (! (T || R))*. T. (! R)*. R] false ");
	   addPattern("After Q", "Precedence Chain 2", "[(! Q)*. Q. (! P)*. S. (! T)*. T] false ");
	   addPattern("Between Q and R", "Precedence Chain 2", "  [true*. Q. (! (P || R))*. S. (! (T || R))*. T. (! R)*. R] false ");
	   addPattern("After Q until R", "Precedence Chain 2", " [true*. Q. (! (P || R))*. S. (! (T || R))*. T] false ");
	   
	   addPattern("Globally", "Response Chain 1", " [true*. S. (! T)*. T] mu X. <true> true && [! P] X ");
	   addPattern("Before R", "Response Chain 1", " [true*. S. (! T)*. T. (! P)*. R] false ");
	   addPattern("After Q", "Response Chain 1", " [(! Q)*. Q. true*. S. (! T)*. T] mu X. <true> true && [! P] X " );
	   addPattern("Between Q and R", "Response Chain 1", "[true*. Q. (! (S || R))*. S. (! (T || R))*. T.(! (P || R))*. R] false");
	   addPattern("After Q until R", "Response Chain 1", "[true*. Q. (! (S || R))*. S. (! (T || R))*. T] mu X. <true> true && [R] false && [! P] X ");
	   
	   addPattern("Globally", "Response Chain 2", "[true*. P] mu X. <true> true && [S] mu Y. (<true> true && [! T] Y) && [! S] X " );
	   addPattern("Before R", "Response Chain 2", "[(! R)*. P. (! (S || R))*. (nil | (S. (! (T || R))*)). R] false ");
	   addPattern("After Q", "Response Chain 2", "[(! Q)*. Q. true*. P] mu X. <true> true && [S] mu Y. (<true> true && [! T] Y) && [! S] X ");
	   addPattern("Between Q and R", "Response Chain 2", "[true*. Q. (! R)*. P. (! (S || R))*. (nil | (S. (! (T || R))*)). R] false ");
	   addPattern("After Q until R", "Response Chain 2", "[true*. Q. (! R)*. P] mu X. <true> true && [R] false && [S] mu Y. (<true> true && [R] false && [! T] Y) && [! S] X ");
	   
	   
	   addPattern("Globally", "Constrained Response Chain 2", "[true*. P] mu X . <true> true && [S] mu Y. (<true> true && [Z] false && [! T] Y) && [! S] X " );
	   addPattern("Before R", "Constrained Response Chain 2", "[(! R)*. P. (! (S || R))*. (nil | (S. (nil | ((! (T || R))*. Z)). (! (T || R))*)). R] false ");
	   addPattern("After Q", "Constrained Response Chain 2", "[(! Q)*. Q. true*. P] mu X. <true> true && [S] mu Y. (<true> true && [Z] false && [! T] Y) && [! S] X");
	   addPattern("Between Q and R", "Constrained Response Chain 2", "[true*. Q. (! R)*. P. (! (S || R))*. (nil | (S. (nil | ((! (T || R))*. Z)). (! (T || R))*)). R] false ");
	   addPattern("After Q until R", "Constrained Response Chain 2", "[true*. Q. (! R)*. P] mu X. <true> true && [R] false && [S] mu Y. (<true> true && [Z || R] false && [! T] Y) && [! S] X ");
	   
	   
	  addPattern("Globally", "Bounded Existence", " [(! P)*. P. (! P)*. P] false ");
	  addPattern("Before R", "Bounded Existence", " [(! R)*. P. (! R)*. P. (! R)*. R] false ");
	  addPattern("After Q", "Bounded Existence", " [(! Q)*. Q. (! P)*. P. (! P)*. P] false ");
	  addPattern("Between Q and R", "Bounded Existence", " [true*. Q. (! R)*. P. (! R)*. P. (! R)*. R] false ");
	  addPattern("After Q until R", "Bounded Existence" , " [true*. Q. (! R)*. P. (! R)*. P] false ");
	  ////  ////  ////  ////  ////  ////  ////  ////  ////  ////  ////  ////  ////  ////
	   addPatternMonitorable("Globally", "Absence", Boolean.TRUE );
	   addPatternMonitorable("Before R", "Absence", Boolean.TRUE );
	   addPatternMonitorable("After Q", "Absence", Boolean.TRUE );
	   addPatternMonitorable("Between Q and R", "Absence",Boolean.TRUE );
	   addPatternMonitorable("After Q until R","Absence", Boolean.TRUE );
	   
	   addPatternMonitorable("Globally", "Existence",Boolean.FALSE );
	   addPatternMonitorable("Before R", "Existence", Boolean.TRUE );
	   addPatternMonitorable("After Q", "Existence", Boolean.FALSE );
	   addPatternMonitorable("Between Q and R", "Existence", Boolean.TRUE );
	   addPatternMonitorable("After Q until R", "Existence", Boolean.FALSE );
	   
	   addPatternMonitorable("Globally", "Universality", Boolean.TRUE );
	   addPatternMonitorable("Before R", "Universality", Boolean.TRUE );
	   addPatternMonitorable("After Q", "Universality", Boolean.TRUE );
	   addPatternMonitorable("Between Q and R", "Universality", Boolean.TRUE );
	   addPatternMonitorable("After Q until R", "Universality", Boolean.TRUE );
	   
	   addPatternMonitorable("Globally", "Precedence", Boolean.TRUE );
	   addPatternMonitorable("Before R" , "Precedence" , Boolean.TRUE );
	   addPatternMonitorable("After Q", "Precedence", Boolean.TRUE );
	   addPatternMonitorable("Between Q and R", "Precedence", Boolean.TRUE );
	   addPatternMonitorable("After Q until R", "Precedence",  Boolean.TRUE );
	   
	   
	   addPatternMonitorable("Globally", "Response", Boolean.FALSE );
	   addPatternMonitorable("Before R", "Response", Boolean.TRUE );
	   addPatternMonitorable("After Q", "Response", Boolean.FALSE );
	   addPatternMonitorable("Between Q and R", "Response", Boolean.TRUE );
	   addPatternMonitorable("After Q until R", "Response", Boolean.FALSE );
	   
	   addPatternMonitorable("Globally", "Precedence Chain 1", Boolean.TRUE );
	   addPatternMonitorable("Before R", "Precedence Chain 1", Boolean.TRUE);
	   addPatternMonitorable("After Q", "Precedence Chain 1", Boolean.TRUE);
	   addPatternMonitorable("Between Q and R", "Precedence Chain 1", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Precedence Chain 1", Boolean.TRUE);
	  
	   
	   addPatternMonitorable("Globally", "Precedence Chain 2", Boolean.TRUE);
	   addPatternMonitorable("Before R", "Precedence Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q", "Precedence Chain 2", Boolean.TRUE);
	   addPatternMonitorable("Between Q and R", "Precedence Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Precedence Chain 2",Boolean.TRUE);
	   
	   addPatternMonitorable("Globally", "Response Chain 1", Boolean.FALSE);
	   addPatternMonitorable("Before R", "Response Chain 1", Boolean.FALSE);
	   addPatternMonitorable("After Q", "Response Chain 1", Boolean.FALSE );
	   addPatternMonitorable("Between Q and R", "Response Chain 1", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Response Chain 1", Boolean.FALSE);
	   
	   addPatternMonitorable("Globally", "Response Chain 2", Boolean.FALSE);
	   addPatternMonitorable("Before R", "Response Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q", "Response Chain 2", Boolean.FALSE);
	   addPatternMonitorable("Between Q and R", "Response Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Response Chain 2", Boolean.FALSE);
	   
	   
	   addPatternMonitorable("Globally", "Constrained Response Chain 2", Boolean.FALSE );
	   addPatternMonitorable("Before R", "Constrained Response Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q", "Constrained Response Chain 2", Boolean.FALSE);
	   addPatternMonitorable("Between Q and R", "Constrained Response Chain 2", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Constrained Response Chain 2", Boolean.FALSE);
	   
	   
	   addPatternMonitorable("Globally", "Bounded Existence", Boolean.TRUE);
	   addPatternMonitorable("Before R", "Bounded Existence", Boolean.TRUE);
	   addPatternMonitorable("After Q", "Bounded Existence", Boolean.TRUE);
	   addPatternMonitorable("Between Q and R", "Bounded Existence", Boolean.TRUE);
	   addPatternMonitorable("After Q until R", "Bounded Existence" , Boolean.TRUE);
	  
//	  Questionnaire.answ12.setScope("Globally");
	  Questionnaire.answ12.setScope("Globally");
	  Questionnaire.answ111111.setScope("After Q");
	  Questionnaire.answ111112.setScope("After Q"); //variant, the formula should be adapted
	  
	  Questionnaire.answ111211.setScope("Before R"); //variant, the formula should be adapted
	  Questionnaire.answ111212.setScope("Before R"); 
	  
	  Questionnaire.answ1131111.setScope("After Q until R");
	  Questionnaire.answ1131211.setScope("After Q until R"); //variant, can it be implemented?

	  Questionnaire.answ1131112.setScope("Between Q and R");
	  Questionnaire.answ1131212.setScope("Between Q and R"); //variant, can it be implemented?

	  Questionnaire.aansw1111.setBehavior("Absence");
	  
	  Questionnaire.aansw1112.setBehavior("Existence");
	  Questionnaire.aansw1113.setBehavior("Bounded Existence");
	  Questionnaire.aansw121212.setBehavior("Precedence"); 
//	  Questionnaire.aansw1222.setBehavior("Response");
	  Questionnaire.aansw121112.setBehavior("Response");

	  // OK
	  Questionnaire.aansw131111.setBehavior("Response Chain 2");
	  Questionnaire.aansw131112.setBehavior("Constrained Response Chain 2");

	  
	  
	  Questionnaire.aansw1312.setBehavior("Response Chain 1");
	  Questionnaire.aansw1313.setBehavior("Precedence Chain 1");
	  Questionnaire.aansw1314.setBehavior("Precedence Chain 2");
   }
   
   public void printOut(){
	   System.out.println(patterns.get("Globally").get("Response Chain 2"));
   }

}
	
