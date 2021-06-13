package p;

import com.jk.web.embedded.JKWebApplication;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Operations {

	String name, id, salary;

	File file = new File("C:\\Users\\DeLL\\Op-File.txt");

	// Create File
	public void createFile() {
		try {
			// file = new File("C:\\Users\\DeLL\\Op-File.txt"); // initialize File object
			// and passing path as argument
			if (file.createNewFile()) // test if successfully created a new file
			{
				System.out.println("file created" + file.getCanonicalPath());

			} else {
				System.out.println("File already exist at location" + file.getCanonicalPath());
			}
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}

	// Write to File
	public void writeToFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\DeLL\\Op-File.txt", true));
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter ID, Name, Salary");
			id = sc.next();
			name = sc.next();
			salary = sc.next();
			bw.write(id + "," + name + "," + salary);
			bw.newLine();
			bw.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An Error occureed");
			e.printStackTrace();
		}
		main(null);

	}

	// Read File
	public void readFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DeLL\\Op-File.txt"));
			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		main(null);
	}

	// Update File
	public void updateRecord() {
		try {
			System.out.println("Enter the ID");
			Scanner sc = new Scanner(System.in);
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DeLL\\Op-File.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\DeLL\\Op-File.txt", true));
			String ID;
			ID = sc.next();
			Stream<String> lines = br.lines();
			lines.forEach(line -> {
				if (line == null) {
					return;
				}
				if (line.contains(ID)) {

					String newName, newSalary;
					System.out.println("Enter New Name, New Salary");
					newName = sc.next();
					newSalary = sc.next();
					try {
						bw.write(ID+","+newName+","+newSalary);
						bw.write(line);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			
			});

			br.close();
			bw.close();

		} catch (IOException e) {
			System.out.println("An Error occureed");
			e.printStackTrace();
		}
		main(null);

	}

	// Delete Record
	public void deleteRecord() {

		try {
			File temp = new File("C:\\Users\\DeLL\\temp.txt");
			String ID;
			System.out.println("Enter the ID");
			Scanner sc = new Scanner(System.in);
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DeLL\\Op-File.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\DeLL\\Op-File.txt", true));
			ID = sc.next();
			Stream<String> lines = br.lines();
			lines.forEach(line -> {
				if (line == null) {
					return;
				}
				if (line.contains(ID)) {
					return;
				}
				try {
					BufferedWriter bw2 = new BufferedWriter(new FileWriter("C:\\Users\\DeLL\\Op-File.txt"));
					bw2.write(line);
					bw.write(line);
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			br.close();
			bw.close();

		}

		catch (IOException e) {
			e.printStackTrace();
		}
		main(null);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations op = new Operations();
		System.out.println("Enter the Operation's number");
		System.out.println("1) Create  2)Read  3)Update  4)Delete");
		Scanner sc = new Scanner(System.in);
		int crud = sc.nextInt();
		switch (crud) {
		case 1:
			op.writeToFile();
			break;
		case 2:
			op.readFile();
			break;
		case 3:
			op.updateRecord();
			break;
		case 4:
			op.deleteRecord();
			break;

		}

	}

}
