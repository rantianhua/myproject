package practice;

import java.util.Scanner;

public class Main {
	
	private Vertex root;	//���ڵ�

	//�ڲ���,�������Ľڵ�
	protected class Vertex {
		
		protected int prefix;	//ͳ�ƽڵ�
		protected Vertex[] edges;	//�ڵ㼯��
		
		//���캯������ʼ��
		Vertex() {
			prefix = 0;
			edges = new Vertex[26]; //ÿ���ڵ���26����ĸ����ѡ��
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
			//��ʾ��������Ѿ���ӵ���������
			//return;
		}else {
			//���ʻ�û������꣬��ǰ׺���ֵĽڵ�Ҫ��һ��ͳ�Ƽ�¼
			vertex.prefix ++;
			
			char c = word.charAt(0);
			c = Character.toLowerCase(c);
			int index = c - 'a';
			if(vertex.edges[index] == null) {
				//�ýڵ㲻���ڣ�������һ���ڵ�
				vertex.edges[index] = new Vertex();
			}
			//���õݹ�������
			addWord(vertex.edges[index],word.substring(1));
		}	
	}
	
	public int countPrefixs(String prefixs) {
		return countPrefixs(root,prefixs);
	}

	private int countPrefixs(Vertex vertex, String prefixs) {
		if(prefixs.length() == 0) {
			//��ʾ�Ѿ�������ǰ׺��
			return vertex.prefix;
		}
		char c = prefixs.charAt(0);
		int index = c - 'a';
		if(vertex.edges[index] == null) {
			//��ʾ�����ڸýڵ㣬�������ڸ�ǰ׺�ĵ���
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
