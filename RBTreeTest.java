package rbtree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import rbtree.RBTree.inOrderDo;

/**
 * Java 语言: 二叉查找树
 *
 * @author skywang
 * @date 2013/11/07
 * @editor KnIfER
 * @date 2017/11/18
 */
public class RBTreeTest {
	static RBTree<Integer> tree;
    private static final int a[] = {10, 20, 30, 40, 50, 60, 70, 80, 90,100,110,120};
    private static final boolean mDebugInsert = false;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = false;    // "删除"动作的检测开关(false，关闭；true，打开)

    public static void main(String[] args) {
        int i, ilen = a.length;
        tree=new RBTree<Integer>();

        System.out.printf("== 原始数据: ");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // 设置mDebugInsert=true,测试"添加函数"
            if (mDebugInsert) {
                System.out.printf("== 添加节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

        System.out.printf("== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
        System.out.printf("\n");

        System.out.printf("== min: %s\n", tree.minimum());
        System.out.printf("== max: %s\n", tree.maximum());
        System.out.printf("== details: \n");
        tree.print();
        System.out.printf("\n");

        // 设置mDebugDelete=true,test remove func
        if (mDebugDelete) {
            for(i=0; i<ilen; i++)
            {
                tree.remove(a[i]);

                System.out.printf("== 删除节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

		System.out.println(tree.search(100).key+"");
		System.out.println(tree.xxing(99).key+"");
		System.out.println(tree.xxing(90).key+"");
		System.out.println(tree.xxing(89).key+"");
		//draw our RnB Tree ~。~
        drawTree();
        //don't do this here……
        //tree.clear();
    }





private static void drawTree() {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            // 创建窗口对象
            MyFrame frame = new MyFrame();
            // 显示窗口
            frame.setVisible(true);

        }
    });
	}





public static class MyFrame extends JFrame {
    public static final String TITLE = "红黑树测试";
    public static final int HEIGHT = 500;
    public static final int WIDTH = (int) (HEIGHT/0.618);
    public MyFrame() {
        super();
        initFrame();
    }

    private void initFrame() {
        // 设置 窗口标题 和 窗口大小
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 把窗口位置设置到屏幕的中心
        setLocationRelativeTo(null);
        // 设置窗口的内容面板
        MyPanel panel = new MyPanel(this);
        setContentPane(panel);
    }

}

public static class MyPanel extends JPanel {
    private MyFrame frame;
    //构造
    public MyPanel(MyFrame frame) {
        super();
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)g).setColor(Color.RED);
        ((Graphics2D)g).drawLine(0, 25, 1366, 25);
        ((Graphics2D)g).drawLine(25, 768, 25, 0);
        //((Graphics2D)g).drawLine(50, 50, 200, 50);
        final Graphics2D g2 = (Graphics2D)g.create();
        final float ratio = frame.getWidth()*.8f/(tree.maximum() - tree.minimum());
        final float offset = tree.minimum()*ratio-25;
        //final float ratioVertival = frame.getHeight()*1.f/(tree.maximum() - tree.minimum());
        g2.setFont(new Font(null, Font.ITALIC, 25));
        tree.SetInOrderDo(new inOrderDo(){
        	
			public void dothis(RBTNode n) {
				RBTNode<Integer> n2 = ((RBTNode<Integer>)n);
				if(n2.color==true)
					g2.setColor(Color.black);
				else g2.setColor(Color.red);
				g2.drawString(n2.key+"",n2.key*ratio-offset ,tree.inorderCoounter2*100);
				g2.setColor(Color.BLUE);
				//draw relationship line
				if(n2.parent!=null)
					g2.drawLine((int)(n2.key*ratio-offset) ,tree.inorderCoounter2*100, (int)(n2.parent.key*ratio-offset), (tree.inorderCoounter2-1)*100);

			}});
        tree.inOrderDo();

    }



}




}







