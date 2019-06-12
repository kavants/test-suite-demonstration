package edu.depaul.se433;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import edu.depaul.se433.Orders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Enclosed.class)
public class OrdersTest {

	   //These tests deal with the sales tax calculated in with the Total Shipping Cost
    //The states should be IL, CA, NY

    @RunWith(Parameterized.class)
    public static class parameterizedTests {
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        	//Making sure tax is applied to IL(before shipping), and both
        	//short form and long form of IL are accepted
            {30, ShippingMethod.Standard, "IL", 41.8},
            {30, ShippingMethod.NextDay, "IL", 56.8},
            {30, ShippingMethod.Standard, "Illinois", 41.8},
            {30, ShippingMethod.NextDay, "Illinois", 56.8},
        	//Making sure tax is applied to IL(before shipping), and both
        	//short form and long form of IL are accepted.
            //No standard shipping charge for x > 50
            {50, ShippingMethod.Standard, "IL", 53},
            {50, ShippingMethod.NextDay, "IL", 78},
            {50, ShippingMethod.Standard, "Illinois", 53},
            {50, ShippingMethod.NextDay, "Illinois", 78},
        	//Making sure tax is applied to CA(before shipping), and both
        	//short form and long form of CA are accepted
            {30, ShippingMethod.Standard, "CA", 41.8},
            {30, ShippingMethod.NextDay, "CA", 56.8},
            {30, ShippingMethod.Standard, "California", 41.8},
            {30, ShippingMethod.NextDay, "California", 56.8},
        	//Making sure tax is applied to IL(before shipping), and both
        	//short form and long form of IL are accepted.
            //No standard shipping charge for x > 50
            {50, ShippingMethod.Standard, "CA", 50},
            {50, ShippingMethod.NextDay, "CA", 78},
            {50, ShippingMethod.Standard, "California", 53},
            {50, ShippingMethod.NextDay, "California", 78},
        	//Making sure tax is applied to NY(before shipping), and both
        	//short form and long form of NY are accepted. 
            
            {30, ShippingMethod.Standard, "NY", 41.8},
            {30, ShippingMethod.NextDay, "NY", 56.8},
            {30, ShippingMethod.Standard, "New York", 41.8},
            {30, ShippingMethod.NextDay, "New York", 56.8},
        	//Making sure tax is applied to NY(before shipping), and both
        	//short form and long form of NY are accepted. 
            //No standard shipping charge for x > 50
            {50, ShippingMethod.Standard, "NY", 53},
            {50, ShippingMethod.NextDay, "NY", 78},
            {50, ShippingMethod.Standard, "New York", 53},
            {50, ShippingMethod.NextDay, "New York", 78},
        	//Both short form and long form of non-taxable states are accepted.
            //No taxes applied
            {30, ShippingMethod.Standard, "NV", 40},
            {30, ShippingMethod.NextDay, "NV", 55},
            {30, ShippingMethod.Standard, "Nevada", 40},
            {30, ShippingMethod.NextDay, "Nevada", 55},
            {30, ShippingMethod.Standard, "FL", 40},
            {30, ShippingMethod.NextDay, "FL", 55},
            {30, ShippingMethod.Standard, "Florida", 40},
            {30, ShippingMethod.NextDay, "Florida", 55},
            {30, ShippingMethod.Standard, "CO", 40},
            {30, ShippingMethod.NextDay, "CO", 55},
            {30, ShippingMethod.Standard, "Coloardo", 40},
            {30, ShippingMethod.NextDay, "Colorado", 55},
        	//Both short form and long form of non-taxable states are accepted.
            //No taxes applied. No Standard shipping charge for x >50
            {50, ShippingMethod.Standard, "NV", 50},
            {50, ShippingMethod.NextDay, "NV", 75},
            {50, ShippingMethod.Standard, "Nevada", 50},
            {50, ShippingMethod.NextDay, "Nevada", 75},
            {50, ShippingMethod.Standard, "FL", 50},
            {50, ShippingMethod.NextDay, "FL", 75},
            {50, ShippingMethod.Standard, "Florida", 50},
            {50, ShippingMethod.NextDay, "Florida", 75},
            {50, ShippingMethod.Standard, "CO", 50},
            {50, ShippingMethod.NextDay, "CO", 75},
            {50, ShippingMethod.Standard, "Coloardo", 50},
            {50, ShippingMethod.NextDay, "Colorado", 75},
            //edge cases
            //No taxes applied
            {50.01, ShippingMethod.Standard, "CO", 50.01},
            {50.01, ShippingMethod.NextDay, "CO", 75.01},
            {50.01, ShippingMethod.Standard, "Coloardo", 50.01},
            {50.01, ShippingMethod.NextDay, "Colorado", 75.01},
            {49.99, ShippingMethod.Standard, "CO", 59.99},
            {49.99, ShippingMethod.Standard, "Coloardo", 49.99},
            {49.99, ShippingMethod.Standard, "FL", 59.99},
            {49.99, ShippingMethod.Standard, "Florida", 59.99},
            //edge cases
            // taxes applied before shipping
            {50.01, ShippingMethod.Standard, "NY", 53},
            {50.01, ShippingMethod.Standard, "New York", 53},
            {49.99, ShippingMethod.Standard, "NY", 62.99},
            {49.99, ShippingMethod.Standard, "New York", 62.99},
            
            
        });
    }
    
    
    private double numA;
    private double expected;
    private ShippingMethod ship;
    private String state;
    
   
    public parameterizedTests(double numA, ShippingMethod ship, String state, double expected) {
    	this.numA = numA;
    	this.ship = ship;
    	this.state = state;
    	this.expected = expected;
    }
    
    
    @Test
    public void testOrdersCalculateTotal() {
        assertThat(Orders.calculateTotal(numA, ship, state), is(expected));
    }
   }

//Non-parameterized tests to be used for exception checking     
   public static class NotParameterizedPart {
   
	    double i = Double.MAX_VALUE;
	    double j = Double.MIN_VALUE;
	    
	    double m = Double.POSITIVE_INFINITY;
	    double n = Double.NEGATIVE_INFINITY;

    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroStandardShipping() {
	Orders.calculateTotal(0, ShippingMethod.Standard, "FL");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroNextDayShipping() {
	Orders.calculateTotal(0, ShippingMethod.NextDay, "FL");
      
    } 
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroStandardShippingLongFormState() {
	Orders.calculateTotal(0, ShippingMethod.Standard, "Florida");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroNextDayShippingLongFormState() {
	Orders.calculateTotal(0, ShippingMethod.NextDay, "Florida");
      
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testNegativePurchaseStandardShipping() {
	Orders.calculateTotal(-30, ShippingMethod.Standard, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNegativePurchaseNextDayShipping() {
	Orders.calculateTotal(-30, ShippingMethod.NextDay, "FL");
     
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNegativePurchaseStandardShippingLongFormState() {
	Orders.calculateTotal(-30, ShippingMethod.Standard, "Florida");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNegativePurchaseNextDayShippingLongFormState() {
	Orders.calculateTotal(-30, ShippingMethod.NextDay, "Florida");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroStandardShippingTaxedState() {
	Orders.calculateTotal(0, ShippingMethod.Standard, "NY");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroNextDayShippingTaxedState() {
	Orders.calculateTotal(0, ShippingMethod.NextDay, "NY");
      
    } 
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroStandardShippingLongFormStateTaxedState() {
	Orders.calculateTotal(0, ShippingMethod.Standard, "New York");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPurchaseIsZeroNextDayShippingLongFormStateTaxedState() {
	Orders.calculateTotal(0, ShippingMethod.NextDay, "New York");
      
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testNegativePurchaseStandardShippingTaxedState() {
	Orders.calculateTotal(-30, ShippingMethod.Standard, "NY");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNegativePurchaseNextDayShippingTaxedState() {
	Orders.calculateTotal(-30, ShippingMethod.NextDay, "NY");
     
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNegativePurchaseStandardShippingLongFormStateTaxedState() {
	Orders.calculateTotal(-30, ShippingMethod.Standard, "New York");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNegativePurchaseNextDayShippingLongFormStateTaxedState() {
	Orders.calculateTotal(-30, ShippingMethod.NextDay, "New York");
      
    } 
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNoStateStandardShipping() {
	Orders.calculateTotal(30, ShippingMethod.Standard, "");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateNoStateNextDayShipping() {
	Orders.calculateTotal(30, ShippingMethod.NextDay, "");
      
    }
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateFiftyNoStateStandardShipping() {
	Orders.calculateTotal(50, ShippingMethod.Standard, "");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateFiftyNoStateNextDayShipping() {
	Orders.calculateTotal(50, ShippingMethod.NextDay, "");
      
    }  
   
    @Test(expected=NullPointerException.class)
    public void testCalculateNullStateStandardShipping() {
	Orders.calculateTotal(30, ShippingMethod.Standard, null);
      
    }
    
    @Test(expected=NullPointerException.class)
    public void testCalculateNullStateNextDayShipping() {
	Orders.calculateTotal(30, ShippingMethod.NextDay, null);
      
    }
    @Test(expected=NullPointerException.class)
    public void testCalculateFiftyNullStateStandardShipping() {
	Orders.calculateTotal(50, ShippingMethod.Standard, null);
      
    }
    
    @Test(expected=NullPointerException.class)
    public void testCalculateFiftyNullStateNextDayShipping() {
	Orders.calculateTotal(50, ShippingMethod.NextDay, null);
      
    }   
    
    @Test(expected=NullPointerException.class)
    public void testCalculateFiftyStateNextDayShipping() {
	Orders.calculateTotal(50, ShippingMethod.NextDay, null);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void testCalculateInvalidStateStandardShipping() {
	Orders.calculateTotal(30, ShippingMethod.Standard, "Some invalid entry");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateInvalidStateNextDayShipping() {
	Orders.calculateTotal(30, ShippingMethod.NextDay, "Some invalid entry");
      
    }
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateFiftyInvalidStateStandardShipping() {
	Orders.calculateTotal(50, ShippingMethod.Standard, "Some invalid entry");
      
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateMaxValueStandardShipping() {
	Orders.calculateTotal(i, ShippingMethod.Standard, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateMaxValueNextDayShipping() {
	Orders.calculateTotal(i, ShippingMethod.NextDay, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateMaxValueStandardShippingTaxedState() {
	Orders.calculateTotal(i, ShippingMethod.Standard, "NY");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateMaxValueNextDayShippingTaxedState() {
	Orders.calculateTotal(i, ShippingMethod.NextDay, "NY");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateMinValueStandardShipping() {
	Orders.calculateTotal(i, ShippingMethod.Standard, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateMinValueNextDayShipping() {
	Orders.calculateTotal(i, ShippingMethod.NextDay, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateMinValueStandardShippingTaxedState() {
	Orders.calculateTotal(j, ShippingMethod.Standard, "NY");
    }
    
    
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateMinValueNextDayShippingTaxedState() {
	Orders.calculateTotal(j, ShippingMethod.NextDay, "NY");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateUnderFlowStandardShippingTaxedState() {
	Orders.calculateTotal(j-10, ShippingMethod.NextDay, "NY");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateUnderFlowNextDayShippingTaxedState() {
	Orders.calculateTotal(j-10, ShippingMethod.NextDay, "NY");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculatePositiveInfinityValueStandardShipping() {
	Orders.calculateTotal(m, ShippingMethod.Standard, "FL");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculatePositiveInfinityNextDayShippinge() {
	Orders.calculateTotal(m, ShippingMethod.NextDay, "FL");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculatePositiveInfinityValueStandardShippingTaxedState() {
	Orders.calculateTotal(m, ShippingMethod.Standard, "NY");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculatePositiveInfityNextDayShippingTaxedState() {
	Orders.calculateTotal(m, ShippingMethod.NextDay, "NY");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void   testCalculateNegativeInfinityValueStandardShippingTaxedState() {
	Orders.calculateTotal(n, ShippingMethod.Standard, "NY");
    }
   
    @Test(expected=IllegalArgumentException.class)
    public void  testCalculateNegativeInfinityNextDayShippingTaxedState() {
	Orders.calculateTotal(n, ShippingMethod.NextDay, "NY");
    }
 
   
    @Test(expected=IllegalArgumentException.class)
    public void   testCalculateNegativeInfinityNextDayShipping() {
	Orders.calculateTotal(n, ShippingMethod.NextDay, "FL");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void   testCalculateNegativeInfinityValueStandardShipping() {
	Orders.calculateTotal(n, ShippingMethod.Standard, "FL");
   }
   
   
     }
   
 }
