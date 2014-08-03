package practice;

import java.util.Scanner;

public class Main {
	
	private Vertex root;	//根节点

	//内部类,构造树的节点
	protected class Vertex {
		
		protected int prefix;	//统计节点
		protected Vertex[] edges;	//节点集，
		
		//构造函数，初始化
		Vertex() {
			prefix = 0;
			edges = new Vertex[26]; //每个节点有26个字母可以选择
			for(int i =0;i < edges.length;i++) {
				edges[i] = null;
			}
		}
	}
	
	public Main() {
		root = new Vertex();
	}
	
	public void addWord(String word) {
		addWord(root,word); 
	}
	
	private void addWord(Vertex vertex, String word) {
		if(word.length() == 0) {
			//表示这个单词已经添加到树里面了
			//return;
		}else {
			//单词还没有添加完，即前缀部分的节点要加一条统计记录
			vertex.prefix ++;
			
			char c = word.charAt(0);
			c = Character.toLowerCase(c);
			int index = c - 'a';
			if(vertex.edges[index] == null) {
				//该节点不存在，则新增一个节点
				vertex.edges[index] = new Vertex();
			}
			//利用递归继续添加
			addWord(vertex.edges[index],word.substring(1));
		}	
	}
	
	public int countPrefixs(String prefixs) {
		return countPrefixs(root,prefixs);
	}

	private int countPrefixs(Vertex vertex, String prefixs) {
		if(prefixs.length() == 0) {
			//表示已经搜索完前缀了
			return vertex.prefix;
		}
		char c = prefixs.charAt(0);
		int index = c - 'a';
		if(vertex.edges[index] == null) {
			//表示不存在该节点，即不存在该前缀的单词
			return 0;
		}
		return countPrefixs(vertex.edges[index],prefixs.substring(1));
	}

	public static void main(String[] args) {
		int m,n;
		String words,prefixs;
		Main demo = new Main();
		Scanner scan = new Scanner(System.in);
		m = scan.nextInt();
		for(int i = 0;i < m;i ++) {
			words = scan.next();
			demo.addWord(words);
		}
		n = scan.nextInt();
		for(int i = 0;i < n;i ++) {
			prefixs = scan.next();
			System.out.println(demo.countPrefixs(prefixs));
		}
	}
}
