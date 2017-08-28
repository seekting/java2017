import com.kunhong.design.AbstractFactory.AbstractCreator;
import com.kunhong.design.AbstractFactory.AbstractProductA;
import com.kunhong.design.AbstractFactory.AbstractProductB;
import com.kunhong.design.AbstractFactory.Creator1;
import com.kunhong.design.AbstractFactory.Creator2;

/**
 * 抽象工厂模式
 * @author lyq
 *
 */
public class Test {
	public static void main(String[] args) {
		// 定义出两个工厂
		AbstractCreator creator1 = new Creator1();
		AbstractCreator creator2 = new Creator2();

		// 产生A1对象
		AbstractProductA a1 = creator1.createProductA();
		// 产生2对象
		AbstractProductA a2 = creator2.createProductA();

		// 产生B1对象
		AbstractProductB b1 = creator1.createProductB();
		// 产生B2对象
		AbstractProductB b2 = creator2.createProductB();
	}
}
