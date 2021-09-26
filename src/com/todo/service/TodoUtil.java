package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;


import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc,category,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� �߰�]\n"
				+ "ī�װ� >");
		category = sc.next();
		sc.nextLine();
		System.out.println("���� >");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		System.out.println("���� > ");
		desc = sc.nextLine().trim();
		System.out.println("�������� >");
		due_date = sc.nextLine();
	
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("�߰��Ǿ����ϴ�!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
	
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int num = sc.nextInt();
		if(num<=0 || num> l.getSize()) {
			System.out.printf("���� �ʰ�");
			return;
		}
		l.deleteItem(num-1);
		System.out.println("���� �Ϸ�!");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int num = sc.nextInt();
		if (num<=0 || num> l.getSize()) {
			System.out.println("���� �ʰ�");
			return;
		}
		l.deleteItem(num-1);
		System.out.println("�� ī�װ� > ");
		String new_category = sc.next().trim();
		
		System.out.println("�� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.println("�� ���� > ");
		String new_description = sc.next().trim();
		
		System.out.println("�� �������� > ");
		String new_due_date = sc.nextLine().trim();
		TodoItem t = new TodoItem(new_category,new_title, new_description,new_due_date);
		l.addItem(t);
		System.out.println("�����Ǿ����ϴ�!");
			}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getSize()+"��]");
		for (TodoItem item : l.getList()) {
			System.out.println(l.indexOf(item)+1+". [" + item.getCategory() + "]" + item.getTitle()+ ":"+item.getDesc()+"-"+item.getDue_date()+" <"+item.getCurrent_date()+"> ");

	}
	}
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			for(TodoItem t : l.getList()) {
				w.write(t.toSaveString());
			}
			w.close();
			System.out.println("����");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
}
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				l.addItem(new TodoItem(st.nextToken(), st.nextToken(), st.nextToken(),st.nextToken()));
			}
				br.close();
				System.out.println("�ε� �Ϸ�!");
		}catch(FileNotFoundException e) {
			System.out.println("���� ����");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}