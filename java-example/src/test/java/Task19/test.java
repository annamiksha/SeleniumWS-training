package Task19;

import org.junit.Test;

public class test {

    @Test
    public void basket(){
        App litecart = new App();
        litecart.open();
        litecart.addToBasket(3);
        litecart.clearBasket();
        litecart.quit();
    }
}
