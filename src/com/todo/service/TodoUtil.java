package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�߰�]");
		System.out.println("ī�װ� > ");
		category = sc.next();
		System.out.println("���� > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("���� �ߺ� �Ұ�!");
			return;
		}
		
		sc.nextLine();
		System.out.println("���� > ");
		desc = sc.nextLine().trim();
		System.out.print("���� > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("�߰� �Ϸ�!");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[����]\n" + "��ȣ > ");
		
		int num = sc.nextInt();
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("���� �ʰ�!");
			return;
		}*/

		listAll(l);
		
		System.out.println("���� �ұ��? (y/n)");
		String yn = sc.next();
		
		if("y".equalsIgnoreCase(yn)) {
			l.deleteItem(num);
			System.out.println("���� �Ϸ�!");
		}
		else {
			System.out.println("���� ���!");
			return;
		}
	}


	public static void updateItem(TodoList l) {	
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[����]\n" + "��ȣ > ");
		int num = sc.nextInt();
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("���� �ʰ�!");
			return;
		}*/
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("���� �ߺ� �Ұ�!");
			return;
		}
		
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(num);

		if(l.updateItem(t)>0) {
			System.out.println("���� �Ϸ�!.");
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("\n�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵ�.\n", count);
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int num) {
		int count = 0;
		for(TodoItem item : l.getList(num)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void completeItem(TodoList l, int number) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == number) {
				String new_title = item.getTitle();
				String new_desc = item.getDesc();
				String new_category = item.getCategory();
				String new_due_date = item.getDue_date();
				
				TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
				t.set_is_completed(1);
				t.setId(number);
				System.out.println(t.toString());
				if(l.completeItem(t)>0) {
					System.out.println("�ش� �׸��� �Ϸ� ó����.\n");
				}
				
			}
		}
	}
}
