package edu.depaul.se433;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Enclosed.class)
public class StringUtilTest {
   
	@RunWith(Parameterized.class)
    public static class parameterizedTests {
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        	//add 's' on the end
        	{"cat", "cats"},
        	{"dog", "dogs"},
        	{"house", "houses"},
        	{"egg", "eggs"},
        	
        	// If the singular noun ends in ‑s, -ss, -sh, -ch, -x, or -z, 
        	//add ‑es to the end to make it plural.
        	{"bus", "buses"},
        	{"marsh", "marshes"},
        	{"crux", "cruxes"},
        	{"truss", "trusses"},
        	{"blitz", "blitzes"},
        	
        	// In some cases, singular nouns ending in -s or -z, require 
        	//that you double the -s or -z prior to adding the -es for pluralization.
        	{"fez", "fezzes"},
        	{"gas", "gasses"},
        	
        	// If the noun ends with ‑f or ‑fe, 
        	//the f is often changed to ‑ve before adding the -s to form the plural version.
        	{"wife", "wives"},
        	{"wolf", "wolves"},
        	
        	//some exceptions to previous rule
        	{"chef", "chefs"},
        	{"belief", "beliefs"},
        	{"chef", "chefs"},
        	{"cheif", "cheifs"},
        	
        	//5 If a singular noun ends in ‑y and the letter before the -y 
        	//is a consonant, change the ending to ‑ies to make the noun plural.
        	{"city", "cities"},
        	{"puppy", "puppies"},
        	
        	 //If the singular noun ends in -y and the letter before the -y is a vowel, 
        	 //simply add an -s to make it plural.
         	{"ray", "rays"},
        	{"boy", "boys"},
        	
        	//If the singular noun ends in ‑o, add ‑es to make it plural.
        	{"potato", "potatoes"},
        	{"tomato", "tomatoes"},
        	
        	//some exceptions to previous rule
        	{"photo", "photos"},
        	{"piano", "pianos"},
        	
        	
        	//volcano exception
           	{"volcano", "volcanoes"},
        	{"volcano", "volcanos"},
        	
        	// If the singular noun ends in ‑us, the plural ending is frequently -i.
           	{"cactus", "cacti"},
        	{"focus", "foci"},
        	
        	//If the singular noun ends in ‑is, the plural ending is -es.
           	{"analysis ", "analyses"},
        	{"ellipsis ", "ellipses"},
        	
        	// If the singular noun ends in ‑on, the plural ending is -a.
          	{"phenomenon", "phenomenona"},
        	{"criterion", "criteria"},
        	
        	// Some nouns don’t change at all when they’re pluralized.
           	{"sheep", "sheep"},
        	{"species", "species"},
        	
        	//select list of irregular nouns
        	{"goose", "geese"},
        	{"child", "children"},
         	{"woman", "women"},
        	{"foot", "feet"},
        	{"person", "people"},
        	{"mouse", "mice"},
        	
        	
        });
    }
    
    private String expected;
    private String noun;
    
   
    public parameterizedTests(String noun, String expected) {
    	this.noun = noun;
    	this.expected = expected;
    }
    
    
    @Test
    public void testPluarlize() {
        assertThat(StringUtil.pluralize(noun), is(expected));
    }
}
  
    //Non-parameterized tests used for exception checking
	//Assumes already plural nouns will throw exception
	//Assumes spell checking and misspelled words will throw exception
    public static class NotParameterizedPart {
    	
        @Test(expected=StringIndexOutOfBoundsException.class)
        public void testEmptyString() {
    	StringUtil.pluralize("");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testAlreadyPluralAddSString() {
    	StringUtil.pluralize("rats");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testAlreadyPluralEndingAString() {
    	StringUtil.pluralize("criteria");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testAlreadyPluralEndingAString2() {
    	StringUtil.pluralize("phenomena");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testAlreadyPluralEndingUSString() {
    	StringUtil.pluralize("focus");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testMisspelledString() {
    	StringUtil.pluralize("focsu");
          }
        
        
        @Test(expected=IllegalArgumentException.class)
        public void testMisspelledString2() {
    	StringUtil.pluralize("potatoe");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testMisspelledString3() {
    	StringUtil.pluralize("puppie");
          }
        
        @Test(expected=IllegalArgumentException.class)
        public void testAlreadyPluralIrregularString() {
    	StringUtil.pluralize("people");
          }
        
        @Test(expected=NullPointerException.class)
        public void testNullString() {
    	StringUtil.pluralize(null);
          }        
        
    	
    }
 }

