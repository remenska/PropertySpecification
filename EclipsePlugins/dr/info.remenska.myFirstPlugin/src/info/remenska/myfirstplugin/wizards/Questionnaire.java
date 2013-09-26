package info.remenska.myfirstplugin.wizards;

public class Questionnaire {
	public static TreeNode<String> scopeQuestionTree = new TreeNode<String>("Is the behavior only required to hold within the restricted interval(s) in the event sequence?", true);
	public static TreeNode<String> questionnaire1 = new TreeNode<String>("How many events of primary interest are there in this behavior?", true);
	TreeNode<String> answ11 = scopeQuestionTree.addChild("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence. ", false);
	TreeNode<String> quest111 = answ11.addChild("Which of the following choices best describes the restricted interval(s)?", true);
		TreeNode<String> answ1111 = quest111.addChild("There can be at most one restricted interval in the event sequence and it has a \n starting delimiter, START: the behavior is required to hold from an occurence of START \n through to the end of the event sequence. ", false);
			TreeNode<String> quest11111 = answ1111.addChild("What if there are multiple occurences of START before the end of the event sequence?", true);
				TreeNode<String> answ111111 = quest11111.addChild("Only the first occurence of START starts the restricted interval; later occurences of START do not have an effect.", false);
				TreeNode<String> answ111112 = quest11111.addChild("Only the last occurence of START starts the restricted interval; each occurence of START resets the beginning of a restricted interval. ", false);
		TreeNode<String> answ1112 = quest111.addChild("There can be at most one restricted interval in the event sequence and it has an ending \n delimiter, END: the behavior is required to hold from the start of the event sequence \n through to the first occurence of END. ", false);
			TreeNode<String> quest11121 = answ1112.addChild("if END does not occur, is the behavior still required to hold, until the end of the event sequence?", true);
				TreeNode<String> answ111211 = quest11121.addChild("Yes, if END does not occur, the behavior is required to hold throughout the entire event sequence.", false);
				TreeNode<String> answ111212 = quest11121.addChild("No, if END does not occur, the behavior not required to hold anywhere in the event sequence.", false);

		TreeNode<String> answ1113 = quest111.addChild("A restricted interval in the event sequence can have both a starting delimiter, START, and an \n ending delimiter, END. The behavior is required to hold from an occurence of START \n through to the end of that restricted interval. ", false);
			TreeNode<String> quest11131 = answ1113.addChild("What happens if there are multiple occurences of START without an occurence of END in between them?", true);
				TreeNode<String> answ11311 = quest11131.addChild("Only the first of those occurences of START potentially starts a restricted interval; later occurences of START within that restricted interval do not have an effect.", false);
				TreeNode<String> answ11312 = quest11131.addChild("Only the last of those occurences of START potentially starts a restricted interval; each of those occurences of START resets the beginning of that restricted interval. ", false);
			TreeNode<String> quest11132 = 	answ1113.addChild("If an occurence of START is not followed by an occurence of END, is the behavior still required to hold, until the end of the event sequence?", true);
				TreeNode<String> answ111321 = quest11132.addChild("Yes, if END does not occur subsequently, then the behavior is required to hold until the end of the event sequence.", false);
				TreeNode<String> answ111322 = quest11132.addChild("No, if END does not occur subsequently, then the behavior is not required to hold for the remainder of the event sequence.", false);
			TreeNode<String> quest11133 =  answ1113.addChild("What happens if START occurs after the end of a restricted interval?", true);
				TreeNode<String> answ111331 = quest11133.addChild("That new occurence of START would potentially start a new restricted interval. The situation would thus be the same \n as after the first occurence of START, meaning that the restrictions described on any \n events that take place after that first occurence would again apply.", false);
				TreeNode<String> answ111332 = quest11133.addChild("The new occurence of START does not have an effect; no new occurence of START will ever start a new restricted interval. ", false);
		TreeNode<String> quest112 = answ11.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold? ", true);
			TreeNode<String> answ1121 = quest112.addChild("No.", false);
			TreeNode<String> answ1122 = quest112.addChild("Yes, if exceptional event X occurs, the property behavior may not be required to hold", false);
				TreeNode<String> quest11221 = answ1122.addChild("Please select one of the following options related to the scope:", true);
					TreeNode<String> answ112211 = quest11221.addChild("If the exceptional event occurs outside the designated scope, it will have no effect. It only matters \n when the exceptional event occurs inside the scope.", false);
					TreeNode<String> answ112212 = quest11221.addChild("No matter where the exceptional event occurs, outside or inside the designated scope, it may have an effect", false);
		
TreeNode<String> answ12 = scopeQuestionTree.addChild("No, the behavior is required to hold throughout the entire event sequence", false);
	TreeNode<String> quest121 = answ12.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold?", true);	
		TreeNode<String> answ1211 = quest121.addChild("No.", false);
		TreeNode<String> answ1212 = quest121.addChild("Yes, if exceptional event X occurs, the property behavior may not be required to hold", false);
		    
//////
TreeNode<String> aansw11 = questionnaire1.addChild("One event.", false);
	TreeNode<String> qquest111 = aansw11.addChild("Which of the following choices best describes the restriction on A?", true);
		TreeNode<String> aansw1111 = qquest111.addChild("A is never allowed to occur", false);
			TreeNode<String> qquest1111 = aansw1111.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
				TreeNode<String> aansw11111 = qquest1111.addChild("No.", false);
				TreeNode<String> aansw11112 = qquest1111.addChild("Yes, if exceptional event X occurs when A has not yet occured, the behavior is not required to hold.", false);
					TreeNode<String> qquest111121 = aansw11112.addChild("What if the exceptional event occurs after a behavior violation has been found (A has occured)?", true);
						TreeNode<String> aansw1111211 = qquest111121.addChild("In that case, the event sequence is not acceptable.", false);
						TreeNode<String> aansw1111212 = qquest111121.addChild("Even in that case, the event sequence is still considered acceptable", false);
		TreeNode<String> aansw1112 = qquest111.addChild("A is allowed to occur at least once.", false);				
			TreeNode<String> qquest11121 = aansw1112.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
				TreeNode<String> aansw111211 = qquest11121.addChild("No.", false);
				TreeNode<String> aansw111212 = qquest11121.addChild("Yes, if exceptional event X occurs, A is not required to occur.", false);
		
		TreeNode<String> aansw1113 = qquest111.addChild("A is allowed to occur exactly once.", false);
			TreeNode<String> qquest11131 = aansw1113.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
				TreeNode<String> aansw111311 = qquest11131.addChild("No.", false);
				TreeNode<String> aansw111312 = qquest11131.addChild("Yes, if exceptional event X occurs when A has not yet occured, the behavior is not required to hold.", false);
					TreeNode<String> qquest1113121 = aansw111312.addChild("What if the exceptional event occurs after the violation has been found (A has occured more then once)?", true);
						TreeNode<String> aansw11121211 = qquest1113121.addChild("In that case, the event sequence is not acceptable.", false);
						TreeNode<String> aansw11121212 = qquest1113121.addChild("Even in that case, the event sequence is still considered acceptable.", false);
					TreeNode<String> qquest1113122 = aansw111312.addChild("What if the exceptional event occurs even \n while the behavioral requirement has not been fulfilled (i.e., A has not yet occured)?", true);
						TreeNode<String> aansw11131221 = qquest1113122.addChild("In that case, the behavior is not required to hold.", false);
						TreeNode<String> aansw11131222 = qquest1113122.addChild("In that case, the behavior is still required to hold", false);
					
TreeNode<String> aansw12 = questionnaire1.addChild("Two events.", false);
	TreeNode<String> qquest121 = aansw12.addChild("Which of the following best describes how A and B interact?", true);
		TreeNode<String> aansw1211 = qquest121.addChild("If A occurs, B is required to occur subsequently", false);
			TreeNode<String> qquest12111 = aansw1211.addChild("Is A required to occur?", true);
				TreeNode<String> aansw121111 = qquest12111.addChild("Yes, A is required to occur.", false);
				TreeNode<String> aansw121112 = qquest12111.addChild("No, A is not required to occur.", false);
		TreeNode<String> aansw1212 = qquest121.addChild("B is not allowed to occur until after A occurs.", false);
			TreeNode<String> qquest12121 = aansw1212.addChild("Is A required to occur, whether or not B eventually occurs?", true);
				TreeNode<String> aansw121211 = qquest12121.addChild("Yes, A is required to occur, whether or not B eventually occurs.", false);
				TreeNode<String> aansw121212 = qquest12121.addChild("No, A is not required to occur and, if it does not occur, B is never allowed to occur.", false);
		TreeNode<String> aansw1213 = qquest121.addChild("Both statements describe how A and B interact: if A occurs, B is required to occur subsequently, and B is not allowed to occur until after A occurs.", false);
			TreeNode<String> qquest12131 = aansw1213.addChild("Is A required to occur?", true);
				TreeNode<String> aansw121311 = qquest12131.addChild("Yes, A is required to occur.", false);
				TreeNode<String> aansw121312 = qquest12131.addChild("No, A is not required to occur.", false);
	
	TreeNode<String> quest122 = aansw12.addChild("After A occurs, is A allowed to occur again before the first subsequent B occurs?", true);
		TreeNode<String> aansw1221 = quest122.addChild("Yes, A is allowed to occur again, zero or more times, before the first subsequent B occurs", false);
		TreeNode<String> aansw1222 = quest122.addChild("No, A is not allowed to occur again before the first subsequent B occurs.", false);
	TreeNode<String> quest123 = aansw12.addChild("After A and the first subsequent B occur, is A allowed to occur again?", true);
		TreeNode<String> aansw1232 = quest123.addChild("No, A is not allowed to occur again.", false);
			TreeNode<String> qquest12321 = aansw1232.addChild("Is B allowed to occur again?", true);
				TreeNode<String> aansw123211 = qquest12321.addChild("Yes, B is allowed to occur again zero or more times.", false);
				TreeNode<String> aansw123212 = qquest12321.addChild("No, B is not allowed to occur again.", false);
		TreeNode<String> aansw1231 = quest123.addChild("Yes, A is allowed to occur again.", false);
			TreeNode<String> qquest12311 = aansw1231.addChild("Is B allowed to occur again?", true);
				TreeNode<String> aansw123111 = qquest12311.addChild("No, B is not allowed to occur again.", false);
				TreeNode<String> aansw123112 = qquest12311.addChild("Yes, B is allowed to occur again, but not until after another A occurs. \n If another A does occur, the situation is the same as when the first A occured, meaning that \n the restrictions described on any events that take place after that occurence would again apply", false);
				TreeNode<String> aansw123113 = qquest12311.addChild("Yes, B is allowed to occur again, zero or more times, whether or not another A occurs.", false);
					TreeNode<String> qquest1231131 = aansw123113.addChild("If another A does occur, is the situation the same as when the first A occured, \n meaning that the restrictions described on any events that take place after that occurence would again apply?", true);
						TreeNode<String> aansw12311311 = qquest1231131.addChild("Yes, if A does occur again, the restrictions described on those events would again apply.", false);
						TreeNode<String> aansw12311312 = qquest1231131.addChild("No, even if A does not occur again, there are no restrictions on the occurences of any future events.", false);
		
	TreeNode<String> qquest124 = aansw12.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold?", true);
		TreeNode<String> aansw1241 = qquest124.addChild("No.", false);
		TreeNode<String> aansw1242 = qquest124.addChild("Yes, if exceptional event X occurs, the event sequence may be considered as acceptable.", false);
			TreeNode<String> qquest12421 = aansw1242.addChild("What if the exceptional event occurs after a behavior violation has been found?", true);
				TreeNode<String> aansw124211 = qquest12421.addChild("In that case, the event sequence is not acceptable.", false);
				TreeNode<String> aansw124212 = qquest12421.addChild("Even in that case, the event sequence is still considered acceptable.", false);

}
