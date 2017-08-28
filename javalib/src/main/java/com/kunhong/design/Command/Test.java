package com.kunhong.design.Command;

/**
 * 命令模式 命令模式定义 将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤销的操作。
 * 在命令模式中，命令对象并不知道如何处理命令
 * ，会有相应的接收者对象来真正执行命令。就像电脑的例子，机箱上的按钮并不知道如何处理功能，而是把这个请求转发给主板，
 * 由主板来执行真正的功能，这个主板就相当于命令模式的接收者。
 * 在命令模式中，命令对象和接收者对象的关系，并不是与生俱来的，需要有一个装配的过程，命令模式中的Client对象就来实现这样的功能
 * 。这就相当于在电脑的例子中，有了机箱上的按钮，也有了主板，还需要有一个连接线把这个按钮连接到主板上才行。
 * 命令模式还会提供一个Invoker对象来持有命令对象，就像电脑的例子，机箱上会有多个按钮，这个机箱就相当于命令模式的Invoker对象。这样一来，
 * 命令模式的客户端就可以通过Invoker来触发并要求执行相应的命令了，这也相当于真正的客户是按下机箱上的按钮来操作电脑一样。
 * 
 * @author lyq
 * 
 */
public class Test {
	public static void main(String[] args) {

		// 1：把命令和真正的实现组合起来，相当于在组装机器，

		// 把机箱上按钮的连接线插接到主板上。

		MainBoardApi mainBoard = new GigaMainBoard();

		OpenCommand openCommand = new OpenCommand(mainBoard);

		// 2：为机箱上的按钮设置对应的命令，让按钮知道该干什么

		Box box = new Box();

		box.setOpenCommand(openCommand);

		// 3：然后模拟按下机箱上的按钮

		box.openButtonPressed();

		// 结果
		// 技嘉主板现在正在开机，请等候

		// 接通电源......

		// 设备检查......

		// 装载系统......

		// 机器正常运转起来......

		// 机器已经正常打开，请操作
	}
}