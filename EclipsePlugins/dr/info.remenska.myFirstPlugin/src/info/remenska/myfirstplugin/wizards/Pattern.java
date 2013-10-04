package info.remenska.myfirstplugin.wizards;

import java.util.HashMap;

public class Pattern {
  public static HashMap<String,HashMap<String,String>> patterns;
 
   public static void addPattern(String scope, String behavior, String formula){
	   HashMap<String,String> behaviors = patterns.get(scope);
	   behaviors.put(behavior, formula);
	   patterns.put(scope,behaviors);
   }
   
   public static void fill(){
	   
	   patterns = new HashMap<String,HashMap<String,String>>();
//		  System.out.println(patterns.keySet());
			  patterns.put("Globally", new HashMap<String, String>());
			  patterns.put("Before R", new HashMap<String, String>());
			  patterns.put("After Q", new HashMap<String, String>());
			  patterns.put("Between Q and R", new HashMap<String, String>());
			  patterns.put("After Q until R", new HashMap<String, String>());
			  
	   addPattern("Globally", "Absence", "[true*. P] false" );
	   addPattern("Before R", "Absence", "[(not R)*. P. (not R)*. R] false");
	   addPattern("After Q", "Absence", "[(not Q)*. Q. true*. P] false");
	   addPattern("Between Q and R", "Absence", "[true*. Q. (not R)*. P. (not R)*. R] false" );
	   addPattern("After Q until R", "Absence", "[true*. Q. (not R)*. P] false");
	   
	   addPattern("Globally", "Existence","mu X. <true> true and [not P] X" );
	   addPattern("Before R", "Existence", "[(not P)*. R] false");
	   addPattern("After Q", "Existence", "[(not Q)*. Q] mu X. <true> true and [not P] X");
	   addPattern("Between Q and R", "Existence", "[true*. Q. (not (P or R))*. R] false");
	   addPattern("After Q until R", "Existence", "[true*. Q] mu X. <true> true and [R] false and [not P] X");
	   
	   addPattern("Globally", "Universality", "[true*. not P] false ");
	   addPattern("Before R", "Universality", "[(not R)*. not (P or R). (not R)*. R] false ");
	   addPattern("After Q", "Universality", "[(not Q)*. Q. true*. not P] false ");
	   addPattern("Between Q and R", "Universality", "[true*. Q. (not R)*. not (P or R). (not R)*. R] false");
	   addPattern("After Q until R", "Universality", "[true*. Q. (not R)*. not (P or R)] false ");
	   
	   addPattern("Globally", "Precedence", "[(not S)*. P] false ");
	   addPattern("Before R" , "Precedence" , "[(not (S or R))*. P. (not R)*. R] false " );
	   addPattern("After Q", "Precedence", "[(not Q)*. Q. (not S)*. P] false ");
	   addPattern("Between Q and R", "Precedence", "[true*. Q. (not (S or R))*. P. (not R)*. R] false");
	   addPattern("After Q until R", "Precedence", "[true*. Q. (not (S or R))*. P] false ");
	   
	   addPattern("Globally", "Response", "[true*. P] mu X. <true> true and [not S] X ");
	   addPattern("Before R", "Response", "[(not R)*. P. (not (S or R))*. R] false ");
	   addPattern("After Q", "Response", "[(not Q)*. Q. true*. P] mu X. <true> true and [not S] X " );
	   addPattern("Between Q and R", "Response", "[true*. Q. (not R)*. P. (not (S or R))*. R] false");
	   addPattern("After Q until R", "Response", "[true*. Q. (not R)*. P] mu X. <true> true and [R] false and [not S] X");
	   
	   addPattern("Globally", "Precedence Chain 1", "[(not S)*. (nil | (S. (not T)*)). P] false " );
	   addPattern("Before R", "Precedence Chain 1", " [(not (S or R))*. (nil | (S. (not (T or R))*)). P. (not R)*. R] false ");
	   addPattern("After Q", "Precedence Chain 1", " [(not Q)*. Q. (not S)*. (nil | (S. (not T)*)). P] false ");
	   addPattern("Between Q and R", "Precedence Chain 1", "[true*. Q. (not (S or R))*. (nil | (S. (not (T or R))*)). P.(not R)*. R] false");
	   addPattern("After Q until R", "Precedence Chain 1", " [true*. Q. (not (S or R))*. (nil | (S. (not (T or R))*)). P] false ");
	   
	   addPattern("Globally", "Precedence Chain 2", " [(not P)*. S. (not T)*. T] false ");
	   addPattern("Before R", "Precedence Chain 2", " [(not (P or R))*. S. (not (T or R))*. T. (not R)*. R] false ");
	   addPattern("After Q", "Precedence Chain 2", "[(not Q)*. Q. (not P)*. S. (not T)*. T] false ");
	   addPattern("Between Q and R", "Precedence Chain 2", "  [true*. Q. (not (P or R))*. S. (not (T or R))*. T. (not R)*. R] false ");
	   addPattern("After Q until R", "Precedence Chain 2", " [true*. Q. (not (P or R))*. S. (not (T or R))*. T] false ");
	   
	   addPattern("Globally", "Response Chain 1", " [true*. S. (not T)*. T] mu X. <true> true and [not P] X ");
	   addPattern("Before R", "Response Chain 1", " [true*. S. (not T)*. T. (not P)*. R] false ");
	   addPattern("After Q", "Response Chain 1", " [(not Q)*. Q. true*. S. (not T)*. T] mu X. <true> true and [not P] X " );
	   addPattern("Between Q and R", "Response Chain 1", "[true*. Q. (not (S or R))*. S. (not (T or R))*. T.(not (P or R))*. R] false");
	   addPattern("After Q until R", "Response Chain 1", "[true*. Q. (not (S or R))*. S. (not (T or R))*. T] mu X. <true> true and [R] false and [not P] X ");
	   
	   addPattern("Globally", "Response Chain 2", "[true*. P] mu X. <true> true and [S] mu Y. (<true> true and [not T] Y) and [not S] X " );
	   addPattern("Before R", "Response Chain 2", "[(not R)*. P. (not (S or R))*. (nil | (S. (not (T or R))*)). R] false ");
	   addPattern("After Q", "Response Chain 2", "[(not Q)*. Q. true*. P] mu X. <true> true and [S] mu Y. (<true> true and [not T] Y) and [not S] X ");
	   addPattern("Between Q and R", "Response Chain 2", "[true*. Q. (not R)*. P. (not (S or R))*. (nil | (S. (not (T or R))*)). R] false ");
	   addPattern("After Q until R", "Response Chain 2", "[true*. Q. (not R)*. P] mu X. <true> true and [R] false and [S] mu Y. (<true> true and [R] false and [not T] Y) and [not S] X ");
	   
	   
	   addPattern("Globally", "Constrained Response Chain 2", "[true*. P] mu X . <true> true and [S] mu Y. (<true> true and [Z] false and [not T] Y) and [not S] X " );
	   addPattern("Before R", "Constrained Response Chain 2", "[(not R)*. P. (not (S or R))*. (nil | (S. (nil | ((not (T or R))*. Z)). (not (T or R))*)). R] false ");
	   addPattern("After Q", "Constrained Response Chain 2", "[(not Q)*. Q. true*. P] mu X. <true> true and [S] mu Y. (<true> true and [Z] false and [not T] Y) and [not S] X");
	   addPattern("Between Q and R", "Constrained Response Chain 2", "[true*. Q. (not R)*. P. (not (S or R))*. (nil | (S. (nil | ((not (T or R))*. Z)). (not (T or R))*)). R] false ");
	   addPattern("After Q until R", "Constrained Response Chain 2", "[true*. Q. (not R)*. P] mu X. <true> true and [R] false and [S] mu Y. (<true> true and [Z or R] false and [not T] Y) and [not S] X ");
	   
	   
	  addPattern("Globally", "Bounded Existence", " [(not P)*. P. (not P)*. P] false ");
	  addPattern("Before R", "Bounded Existence", " [(not R)*. P. (not R)*. P. (not R)*. R] false ");
	  addPattern("After Q", "Bounded Existence", " [(not Q)*. Q. (not P)*. P. (not P)*. P] false ");
	  addPattern("Between Q and R", "Bounded Existence", " [true*. Q. (not R)*. P. (not R)*. P. (not R)*. R] false ");
	  addPattern("After Q until R", "Bounded Existence" , " [true*. Q. (not R)*. P. (not R)*. P] false ");
	  
	  
	  
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
	
