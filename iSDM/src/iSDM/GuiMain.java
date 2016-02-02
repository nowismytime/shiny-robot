package iSDM;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GuiMain {
	
	static HashMap<String, Users> UserList = new HashMap<String, Users>();
	static HashMap<String, Books> BookList = new HashMap<String, Books>();
	static ArrayList<Issues> IssueList = new ArrayList<Issues>();
	static ArrayList<Returns> ReturnList = new ArrayList<Returns>();
	
	JFrame frame;
	JLabel label;
	JButton Hbutton;
	
	JPanel jj;
	ArrayList<String> list;
	ArrayList<Issues> ilist;
	int curr;
	int total;
	JFrame cframe;
	
	ArrayList<Books> book3;
	//added to git finally
	
	
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{
		Initialize.inAll(UserList, BookList, IssueList, ReturnList);
		GuiMain gui = new GuiMain();
		gui.go();
	}
	
	public void go()
	{
		frame = new JFrame(); // home frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent event) {
				try {
					exitProcedure();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		JButton Button1 = new JButton("Add a User");
		Button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Users nuser = new Users();
				
				frame.setVisible(false);
				
				JFrame frame1 = new JFrame(); // add user- 1st page frame
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame1.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JPanel dp = new JPanel();
				dp.setLayout(new GridLayout(5,1));
				
				//home button
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				//Hbutton.setPreferredSize(new Dimension(10, 10));
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				// add user -1st page components
				JLabel l1 = new JLabel("Enter Unique User ID:", SwingConstants.CENTER);
				JLabel l2 = new JLabel("Error: User Already Exists.", SwingConstants.CENTER);
				JLabel l3 = new JLabel("Error: please enter a valid unique id", SwingConstants.CENTER);
				JTextField txt1 = new JTextField(10);
				
				JButton b2 = new JButton("Add User");
				b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String rNum = txt1.getText();
								JFrame frame11 = new JFrame(); // add user - 2nd page frame
								frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							    frame11.addWindowListener(new WindowAdapter() {
							        @Override
							        public void windowClosing(WindowEvent event) {
							            try {
											exitProcedure();
										} catch (IOException e) {
											e.printStackTrace();
										}
							        }
							    });
							    JPanel dp1 = new JPanel();
							    if (rNum.isEmpty())
							    {
							    	dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l3);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);
							    }
							    else if (UserList.containsKey(rNum))
								{
									// add user - 2nd page if user exists
									dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l2);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);
								}
								else
								{
									//add user - 2nd page of user doesn't exist
									JPanel n1 = new JPanel();
									JLabel l11 = new JLabel("Name");
									JTextField t1 = new JTextField(20);
									n1.add(l11);
									n1.add(t1);
									
									JPanel n2 = new JPanel();
									JLabel l12 = new JLabel("Branch");
									JTextField t2 = new JTextField(20);
									n2.add(l12);
									n2.add(t2);
									
									JPanel n3 = new JPanel();
									JLabel l13 = new JLabel("Semester");
									JTextField t3 = new JTextField(20);
									n3.add(l13);
									n3.add(t3);
									
									JPanel n4 = new JPanel();
									JLabel l14 = new JLabel("Mobile:");
									JTextField t4 = new JTextField(20);
									n4.add(l14);
									n4.add(t4);
									
									JPanel n5 = new JPanel();
									JLabel l15 = new JLabel("Email");
									JTextField t5 = new JTextField(20);
									n5.add(l15);
									n5.add(t5);
									
									JButton nb1 = new JButton("Add User");
									nb1.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													nuser.rNum = rNum;
													nuser.Name = t1.getText();
													nuser.Branch = t2.getText();
													nuser.Semester = t3.getText();
													nuser.Mob = t4.getText();
													nuser.email = t5.getText();
													nuser.iBooks=0;
													UserList.put(rNum, nuser);
													
													JFrame f111 = new JFrame();
													f111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												    f111.addWindowListener(new WindowAdapter() {
												        @Override
												        public void windowClosing(WindowEvent event) {
												            try {
																exitProcedure();
															} catch (IOException e) {
																e.printStackTrace();
															}
												        }
												    });
													f111.getContentPane().add(BorderLayout.NORTH, Hbutton);
													JLabel lend = new JLabel("User added Successfully.");
													f111.getContentPane().add(lend);
													f111.setSize(400, 200);
													frame11.setVisible(false);
													f111.setVisible(true);
													
													
												}
											});
									
									dp1.setLayout(new BoxLayout(dp1, BoxLayout.Y_AXIS));
									
									dp1.add(Hbutton);
									dp1.add(n1);
									dp1.add(n2);
									dp1.add(n3);
									dp1.add(n4);
									dp1.add(n5);
									dp1.add(nb1);
									frame11.setContentPane(dp1);
									frame11.setSize(310,300);
								}
								frame11.setVisible(true);
								frame1.setVisible(false);
							}
						});			
				dp.add(Hbutton);
				dp.add(l1);
				dp.add(txt1);
				dp.add(b2);
				
				frame1.setContentPane(dp);
				frame1.setSize(300, 200);
				frame1.setVisible(true);
			}
		});
		
		JButton Button2 = new JButton("Add a Book");
		Button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Books nuser = new Books();
				
				frame.setVisible(false);
				
				JFrame frame1 = new JFrame(); // add book- 1st page frame
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame1.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JPanel dp = new JPanel();
				dp.setLayout(new GridLayout(5,1));
				
				//home button
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				//Hbutton.setPreferredSize(new Dimension(10, 10));
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				// add book -1st page components
				JLabel l1 = new JLabel("Enter Unique Book ID:", SwingConstants.CENTER);
				JLabel l2 = new JLabel("Error: Book ID Already Exists.", SwingConstants.CENTER);
				JLabel l3 = new JLabel("Error: Enter Valid Book Id.", SwingConstants.CENTER);
				JTextField txt1 = new JTextField(10);
				
				JButton b2 = new JButton("Add Book");
				b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String rNum = txt1.getText();
								JFrame frame11 = new JFrame(); // add book - 2nd page frame
								frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							    frame11.addWindowListener(new WindowAdapter() {
							        @Override
							        public void windowClosing(WindowEvent event) {
							            try {
											exitProcedure();
										} catch (IOException e) {
											e.printStackTrace();
										}
							        }
							    });
							    JPanel dp1 = new JPanel();
								
							    if (rNum.isEmpty())
							    {
							    	dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l3);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);
							    }
							    else if (BookList.containsKey(rNum))
								{
									// add book - 2nd page if user exists
									dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l2);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);	
								}
								else
								{
									//add book - 2nd page of user doesn't exist
									JPanel n1 = new JPanel();
									JLabel l11 = new JLabel("ISBN");
									JTextField t1 = new JTextField(20);
									n1.add(l11);
									n1.add(t1);
									
									JPanel n2 = new JPanel();
									JLabel l12 = new JLabel("Title");
									JTextField t2 = new JTextField(20);
									n2.add(l12);
									n2.add(t2);
									
									JPanel n3 = new JPanel();
									JLabel l13 = new JLabel("Authors");
									JTextArea t3 = new JTextArea(2,20);
									n3.add(l13);
									n3.add(t3);
									
									JPanel n4 = new JPanel();
									JLabel l14 = new JLabel("Edition");
									JTextField t4 = new JTextField(20);
									n4.add(l14);
									n4.add(t4);
									
									JPanel n5 = new JPanel();
									JLabel l15 = new JLabel("Field of Study");
									JTextField t5 = new JTextField(15);
									n5.add(l15);
									n5.add(t5);
									
									JPanel n6 = new JPanel();
									JLabel l16 = new JLabel("Publisher");
									JTextField t6 = new JTextField(20);
									n6.add(l16);
									n6.add(t6);
									
									JPanel n7 = new JPanel();
									JLabel l17 = new JLabel("Period of Issue");
									JTextField t7 = new JTextField(15);
									n7.add(l17);
									n7.add(t7);
									
									JButton nb1 = new JButton("Add Book");
									nb1.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													nuser.BookId = rNum;
													nuser.ISBN = t1.getText();
													nuser.title = t2.getText();
													nuser.edition = Integer.parseInt(t4.getText());
													nuser.subject = t5.getText();
													nuser.Publisher = t6.getText();
													nuser.days = Integer.parseInt(t7.getText());
												
													String s[] = t3.getText().split("\\r?\\n");
												    ArrayList<String> arrList = new ArrayList<>(Arrays.asList(s));
												    nuser.authors = arrList;
												    
													BookList.put(rNum, nuser);
													
													JFrame f111 = new JFrame();
													f111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												    f111.addWindowListener(new WindowAdapter() {
												        @Override
												        public void windowClosing(WindowEvent event) {
												            try {
																exitProcedure();
															} catch (IOException e) {
																e.printStackTrace();
															}
												        }
												    });
													f111.getContentPane().add(BorderLayout.NORTH, Hbutton);
													JLabel lend = new JLabel("Book added Successfully.");
													f111.getContentPane().add(lend);
													f111.setSize(400, 200);
													frame11.setVisible(false);
													f111.setVisible(true);
												}
											});
									
									dp1.setLayout(new GridLayout(9,1));
									
									dp1.add(Hbutton);
									dp1.add(n1);
									dp1.add(n2);
									dp1.add(n3);
									dp1.add(n4);
									dp1.add(n5);
									dp1.add(n6);
									dp1.add(n7);
									dp1.add(nb1);
									frame11.setContentPane(dp1);
									frame11.setSize(310,400);
								}
								frame11.setVisible(true);
								frame1.setVisible(false);
							}
						});			
				dp.add(Hbutton);
				dp.add(l1);
				dp.add(txt1);
				dp.add(b2);
				
				frame1.setContentPane(dp);
				frame1.setSize(300, 200);
				frame1.setVisible(true);
			}
		});

		JButton Button3 = new JButton("Find Issued Books");
		Button3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JPanel dp = new JPanel();
				JPanel dp2 = new JPanel();
				dp.setLayout(new GridLayout(10,1));
				
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel dp1 = new JPanel();
				
				JButton b1 = new JButton("Student");
				b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								JPanel d1p = new JPanel();
								JPanel d2p = new JPanel();
								JPanel d5p = new JPanel();
								d1p.setLayout(new GridLayout(10,1));
								
								JLabel jp = new JLabel("Enter your Unique ID", SwingConstants.CENTER);
								JTextField jt = new JTextField();
								
								JButton b1b = new JButton("Find Issued Books");
								b1b.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent e)
											{
												String rNum = jt.getText();
												if (!UserList.containsKey(rNum))
												{
													JPanel dp3 = new JPanel();
													dp3.setLayout(new GridLayout(10,1));
													JLabel jp1 = new JLabel("Error: User not found!", SwingConstants.CENTER);
													dp3.add(Hbutton);
													dp3.add(dp2);
													dp3.add(dp1);
													dp3.add(d2p);
													dp3.add(jp);
													dp3.add(jt);
													dp3.add(d5p);
													dp3.add(b1b);
													dp3.add(jp1);
													frame11.setContentPane(dp3);
												}
												else
												{
													int count=0;
													list = new ArrayList<String>();
													ilist = new ArrayList<Issues>();
													for (int i=0;i<IssueList.size();i++)
													{
														Issues nIssue = IssueList.get(i);
														if (nIssue.rNum.equals(rNum))
														{
															count++;
															list.add(nIssue.BookId);
															ilist.add(nIssue);
														}
													}
													if (list.size()==0)
													{
														JPanel dp3 = new JPanel();
														dp3.setLayout(new GridLayout(10,1));
														JLabel jp1 = new JLabel("You have no active issues!", SwingConstants.CENTER);
														dp3.add(Hbutton);
														dp3.add(dp2);
														dp3.add(dp1);
														dp3.add(d2p);
														dp3.add(jp);
														dp3.add(jt);
														dp3.add(d5p);
														dp3.add(b1b);
														dp3.add(jp1);
														frame11.setContentPane(dp3);
													}
													else
													{
														curr=1;
														total=count;
														cframe=frame11;
														JPanel jp = pageShow(list, ilist, 1, count, frame, frame11);
														frame11.setContentPane(jp);
													}
												}
												for (Frame f : Frame.getFrames())
												{
													if (f.isVisible())
														f.setVisible(false);
												}
												frame11.setVisible(true);
											}
										});
								
								d1p.add(Hbutton);
								d1p.add(dp2);
								d1p.add(dp1);
								d1p.add(d2p);
								d1p.add(jp);
								d1p.add(jt);
								d1p.add(d5p);
								d1p.add(b1b);
								frame11.setContentPane(d1p);
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);							
							}
						});
				
				JButton b2 = new JButton("Staff");
				b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								if (IssueList.size()==0)
								{
									JPanel dp1 = new JPanel();
									JLabel jp1 = new JLabel("No books are issued yet!", SwingConstants.CENTER);
									dp1.add(b1);
									dp1.add(b2);
									JPanel dp = new JPanel();
									dp.setLayout(new GridLayout(10,1));
									dp.add(Hbutton);
									dp.add(dp2);
									dp.add(dp1);
									dp.add(jp1);
									frame11.setContentPane(dp);
								}
								else
								{
									curr=1;
									cframe=frame11;
									JPanel jp = spageShow(1, frame, frame11);
									frame11.setContentPane(jp);
								}
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);
							}
						});
				
				
				dp1.add(b1);
				dp1.add(b2);
			
				dp.add(Hbutton);
				dp.add(dp2);
				dp.add(dp1);
				frame11.setContentPane(dp);
				frame11.setSize(600, 700);
				frame11.setVisible(true);
			}
		});

		JButton Button4 = new JButton("Search a Book");
		Button4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mainPanel = new JPanel();
				JPanel ePanel = new JPanel();
				
				JPanel title1 = new JPanel();
				JLabel bt = new JLabel("Book Title: ");
				JTextField jt = new JTextField(46);
				title1.add(bt);
				title1.add(jt);
				
				JPanel title2 = new JPanel();
				JLabel bt2 = new JLabel("Book Author: ");
				JTextField jt2 = new JTextField(44);
				title2.add(bt2);
				title2.add(jt2);
				
				JPanel title3 = new JPanel();
				JLabel bt3 = new JLabel("Book Author: ");
				JTextField jt3 = new JTextField(44);
				title3.add(bt3);
				title3.add(jt3);
				
				JPanel title4 = new JPanel();
				JLabel bt4 = new JLabel("Book Publisher: ");
				JTextField jt4 = new JTextField(42);
				title4.add(bt4);
				title4.add(jt4);
				
				JPanel title5 = new JPanel();
				JLabel bt5 = new JLabel("Book ISBN: ");
				JTextField jt5 = new JTextField(46);
				title5.add(bt5);
				title5.add(jt5);
				
				JPanel title6 = new JPanel();
				JLabel bt6 = new JLabel("Book ID: ");
				JTextField jt6 = new JTextField(47);
				title6.add(bt6);
				title6.add(jt6);
				
				JPanel s1 = new JPanel();
				JButton search = new JButton("Search");
				s1.add(search);
				
				mainPanel.setLayout(new GridLayout(9,1));
				
				mainPanel.add(Hbutton);
				mainPanel.add(ePanel);
				mainPanel.add(title1);
				mainPanel.add(title2);
				mainPanel.add(title3);
				mainPanel.add(title4);
				mainPanel.add(title5);
				mainPanel.add(title6);
				mainPanel.add(s1);
				
				frame11.setContentPane(mainPanel);
				frame11.setSize(600, 700);
				frame11.setVisible(true);
				
				search.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String title = jt.getText();
								String a1 = jt2.getText();
								String a2 = jt3.getText();
								String pub = jt4.getText();
								String isbn = jt5.getText();
								String id = jt6.getText();
								
								ArrayList<Books> book1 = new ArrayList<Books>();
								for (String key : BookList.keySet())
									book1.add(BookList.get(key));
								
								if (!title.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).title.equals(title))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!a1.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										Books nbook = book1.get(i);
										if (nbook.authors.get(0).equals(a1)||(nbook.authors.size()>1&&nbook.authors.get(1).equals(a1)))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!a2.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										Books nbook = book1.get(i);
										if (nbook.authors.get(0).equals(a2)||(nbook.authors.size()>1&&nbook.authors.get(1).equals(a2)))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!pub.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).Publisher.equals(pub))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!isbn.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).ISBN.equals(isbn))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!id.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).BookId.equals(id))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								
								/*if (title.isEmpty()&&a1.isEmpty()&&a2.isEmpty()&&pub.isEmpty()&&isbn.isEmpty()&&id.isEmpty())
								{
									book3 = book1;
									curr=1;
									cframe=frame11;
									JPanel jp = searchShow(1, frame, frame11);
									frame11.setContentPane(jp);
									/*JLabel lb = new JLabel("Error: Please Enter at least one field", SwingConstants.CENTER);
									JPanel mainPanel1 = new JPanel();
									mainPanel1.setLayout(new GridLayout(10,1));
									
									mainPanel1.add(Hbutton);
									mainPanel1.add(ePanel);
									mainPanel1.add(title1);
									mainPanel1.add(title2);
									mainPanel1.add(title3);
									mainPanel1.add(title4);
									mainPanel1.add(title5);
									mainPanel1.add(title6);
									mainPanel1.add(s1);
									mainPanel1.add(lb);
									frame11.setContentPane(mainPanel1);
								}*/
								if (book1.size()==0)
								{
									JLabel lb = new JLabel("Sorry, No such book found", SwingConstants.CENTER);
									JPanel mainPanel1 = new JPanel();
									mainPanel1.setLayout(new GridLayout(10,1));
									
									mainPanel1.add(Hbutton);
									mainPanel1.add(ePanel);
									mainPanel1.add(title1);
									mainPanel1.add(title2);
									mainPanel1.add(title3);
									mainPanel1.add(title4);
									mainPanel1.add(title5);
									mainPanel1.add(title6);
									mainPanel1.add(s1);
									mainPanel1.add(lb);
									frame11.setContentPane(mainPanel1);
								}
								else
								{
									book3 = book1;
									curr=1;
									cframe=frame11;
									JPanel jp = searchShow(1, frame, frame11);
									frame11.setContentPane(jp);
									
								}
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);
							}
						});
			}
		});

		JButton Button5 = new JButton("Issue a Book");
		Button5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mainPanel = new JPanel();
				JPanel ep1 = new JPanel();
				JPanel ep2 = new JPanel();
				JPanel ep3 = new JPanel();
				JPanel jp1 = new JPanel();
				
				JLabel userid = new JLabel("Enter Unique User ID:", SwingConstants.CENTER);
				JLabel bookid = new JLabel("Enter Unique Book ID", SwingConstants.CENTER);
				
				JTextField ui = new JTextField();
				JTextField bi = new JTextField();
				
				JButton b1 = new JButton("Issue Book");
				jp1.add(b1);
				
				mainPanel.setLayout(new GridLayout(9,1));
				mainPanel.add(Hbutton);
				mainPanel.add(ep1);
				mainPanel.add(userid);
				mainPanel.add(ui);
				mainPanel.add(ep2);
				mainPanel.add(bookid);
				mainPanel.add(bi);
				mainPanel.add(ep3);
				mainPanel.add(jp1);
				
				frame11.setContentPane(mainPanel);
				frame11.setSize(600, 500);
				frame11.setVisible(true);
				
				b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								JPanel mainPanel1 = new JPanel();
								
								JLabel err1 = new JLabel("Error: User does not exist.", SwingConstants.CENTER);
								JLabel err2 = new JLabel("Sorry, you have already issued 5 books. Please return some of them to issue new ones", SwingConstants.CENTER);
								JLabel err3 = new JLabel("Error: Book does not exist.", SwingConstants.CENTER);
								JLabel err4 = new JLabel("Error: Book already issued.", SwingConstants.CENTER);
								JLabel err5 = new JLabel("Error: Please enter both the fields.", SwingConstants.CENTER);
								JLabel jl1 = new JLabel("Book Issued successfully", SwingConstants.CENTER);
								
								Issues nIssue = new Issues();
								nIssue.rNum=ui.getText();
								nIssue.BookId=bi.getText();
								
								mainPanel1.setLayout(new GridLayout(11,1));
								mainPanel1.add(Hbutton);
								mainPanel1.add(ep1);
								mainPanel1.add(userid);
								mainPanel1.add(ui);
								mainPanel1.add(ep2);
								mainPanel1.add(bookid);
								mainPanel1.add(bi);
								mainPanel1.add(ep3);
								mainPanel1.add(jp1);
								
								if (nIssue.BookId.isEmpty()||nIssue.rNum.isEmpty())
									mainPanel1.add(err5);
								else
								{
									Users nuser = UserList.get(nIssue.rNum);
									if (!UserList.containsKey(nIssue.rNum))
										mainPanel1.add(err1);
									else if (nuser.iBooks>=5)
									{
										mainPanel1.add(err2);
									}
									if (!BookList.containsKey(nIssue.BookId))
									{
										mainPanel1.add(err3);
									}
									else
									{
										int i;
										for (i=0;i<IssueList.size();i++)
										{
											Issues nIssue1 = IssueList.get(i);
											if(nIssue1.BookId.equals(nIssue.BookId))
											{
												mainPanel1.add(err4);
												break;
											}
										}
										if (i==IssueList.size())
										{
											Date date = new Date();
											SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
											String fdate = sdf.format(date);
											nIssue.DOI=fdate;
											nuser.iBooks += 1;
											IssueList.add(nIssue);
											mainPanel1.add(jl1);
										}
									}
								}
								frame11.setContentPane(mainPanel1);
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);								
							}
						});
			}
		});

		JButton Button6 = new JButton("Return a Book");
		Button6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mainPanel = new JPanel();
				JPanel ep1 = new JPanel();
				JPanel ep2 = new JPanel();
				JPanel ep3 = new JPanel();
				JPanel jp1 = new JPanel();
				
				JLabel userid = new JLabel("Enter Unique User ID:", SwingConstants.CENTER);
				JLabel bookid = new JLabel("Enter Unique Book ID", SwingConstants.CENTER);
				
				JTextField ui = new JTextField();
				JTextField bi = new JTextField();
				
				JButton b1 = new JButton("Return Book");
				jp1.add(b1);
				
				mainPanel.setLayout(new GridLayout(9,1));
				mainPanel.add(Hbutton);
				mainPanel.add(ep1);
				mainPanel.add(userid);
				mainPanel.add(ui);
				mainPanel.add(ep2);
				mainPanel.add(bookid);
				mainPanel.add(bi);
				mainPanel.add(ep3);
				mainPanel.add(jp1);
				
				frame11.setContentPane(mainPanel);
				frame11.setSize(600, 500);
				frame11.setVisible(true);
				
				b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								JPanel mainPanel1 = new JPanel();
								
								JLabel err1 = new JLabel("Error: User does not exist.", SwingConstants.CENTER);
								JLabel err2 = new JLabel("Error: The book with the given Book Id is not issued to you.", SwingConstants.CENTER);
								JLabel err3 = new JLabel("Error: Book does not exist.", SwingConstants.CENTER);
								JLabel err4 = new JLabel("Thank You for returning the book in time.", SwingConstants.CENTER);
								JLabel err5 = new JLabel("Error: Please enter both the fields.", SwingConstants.CENTER);
								
								
								Returns nreturn = new Returns();
								nreturn.rNum=ui.getText();
								nreturn.BookId=bi.getText();
								
								mainPanel1.setLayout(new GridLayout(11,1));
								mainPanel1.add(Hbutton);
								mainPanel1.add(ep1);
								mainPanel1.add(userid);
								mainPanel1.add(ui);
								mainPanel1.add(ep2);
								mainPanel1.add(bookid);
								mainPanel1.add(bi);
								mainPanel1.add(ep3);
								mainPanel1.add(jp1);
								
								if (nreturn.BookId.isEmpty()||nreturn.rNum.isEmpty())
									mainPanel1.add(err5);
								else
								{
									if (!UserList.containsKey(nreturn.rNum))
										mainPanel1.add(err1);
									if (!BookList.containsKey(nreturn.BookId))
										mainPanel1.add(err3);
									else
									{
										int i;
										for (i=0;i<IssueList.size();i++)
										{
											Issues nIssue = IssueList.get(i);
											if(nIssue.BookId.equals(nreturn.BookId))
											{
												if(!nIssue.rNum.equals(nreturn.rNum))
													mainPanel1.add(err2);
												else
													nreturn.DOI=nIssue.DOI;
												break;
											}
										}
										if (i==IssueList.size())
											mainPanel1.add(err2);
										else
										{
											Books nBook = BookList.get(nreturn.BookId);
											Date date = new Date();
											SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
											String fdate = sdf.format(date);
											nreturn.DOR=fdate;
											int days = calculate(nreturn.DOI, nreturn.DOR);
											nreturn.expd = ceDOR(nreturn.DOI,nBook.days);
											if (days<=nBook.days)
											{
												mainPanel1.add(err4);
												nreturn.fine=0;
												nreturn.paid=true;
											}
											else
											{
												nreturn.fine=(days-nBook.days);
												JLabel jl1 = new JLabel("Your fine is Rs."+nreturn.fine, SwingConstants.CENTER);
												mainPanel1.add(jl1);
											}
											for (int i1=0;i1<IssueList.size();i1++)
											{
												Issues temp1 = IssueList.get(i1);
												if (temp1.BookId.equals(nreturn.BookId))
												{
													IssueList.remove(temp1);
													break;
												}
											}
											Users nuser = UserList.get(nreturn.rNum);
											nuser.iBooks -= 1;
											ReturnList.add(nreturn);
										}
									}
								}								
								frame11.setContentPane(mainPanel1);
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);								
							}
						});
			}
		});

		JButton Button7 = new JButton("Pay Library Fine");
		Button7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
			    JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mp1 = new JPanel();
				JPanel mp2 = new JPanel();
				JPanel mp3 = new JPanel();
				JPanel mp4 = new JPanel();
				JLabel lb1 = new JLabel("Enter your unique ID:", SwingConstants.CENTER);
				JTextField tf1 = new JTextField(20);
				JPanel ep1 = new JPanel();
				
				JButton b1 = new JButton("Search");
				
				mp1.setLayout(new GridLayout(5,1));
				mp2.add(Hbutton);
				mp1.add(mp2);				
				mp1.add(ep1);
				mp3.add(tf1);
				mp1.add(lb1);
				mp1.add(mp3);
				mp4.add(b1);
				mp1.add(mp4);
				
				frame11.setContentPane(mp1);
				frame11.setSize(600, 500);
				frame11.setVisible(true);
				
				b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String rNum = tf1.getText();
								if (rNum.isEmpty())
								{
									JPanel m1p = new JPanel();
									m1p.setLayout(new GridLayout(6,1));
									JLabel lb2 = new JLabel("The above field is mandatory.", SwingConstants.CENTER);
									m1p.add(mp2);
									m1p.add(ep1);
									m1p.add(lb1);
									m1p.add(mp3);
									m1p.add(mp4);
									m1p.add(lb2);
									frame11.setContentPane(m1p);
								}
								else if (!UserList.containsKey(rNum))
								{
									JPanel m1p = new JPanel();
									m1p.setLayout(new GridLayout(6,1));
									JLabel lb2 = new JLabel("Error: User doesn't exist.", SwingConstants.CENTER);
									m1p.add(mp2);
									m1p.add(ep1);
									m1p.add(lb1);
									m1p.add(mp3);
									m1p.add(mp4);
									m1p.add(lb2);
									frame11.setContentPane(m1p);
								}
								else
								{
									JPanel mainPanel1 = new JPanel();
									
									ArrayList<Integer> list22 = new ArrayList<Integer>();
									int fine=0;
									for(int i=0;i<ReturnList.size();i++)
									{
										Returns nreturn = ReturnList.get(i);
										if(nreturn.rNum.equals(rNum)&&!nreturn.paid)
										{
											list22.add(i);
											fine += nreturn.fine;
										}
									}
									if (fine==0)
									{
										JPanel m1p = new JPanel();
										m1p.setLayout(new GridLayout(6,1));
										JLabel lb2 = new JLabel("You have no fine due.", SwingConstants.CENTER);
										m1p.add(mp2);
										m1p.add(ep1);
										m1p.add(lb1);
										m1p.add(mp3);
										m1p.add(mp4);
										m1p.add(lb2);
										frame11.setContentPane(m1p);
									}
									else
									{
										JLabel jl1 = new JLabel("Your total fine is: Rs. "+fine, SwingConstants.CENTER);
										JLabel jl2 = new JLabel("against the following:", SwingConstants.CENTER);
									
										DefaultTableModel model = new DefaultTableModel();
										JTable table = new JTable(model);
									
										model.addColumn("Book ID");
										model.addColumn("Expected D.O.R");
										model.addColumn("Actual D.O.R");
									
										model.addRow(new Object[]{"Book ID", "Expected D.O.R", "Actual D.O.R"});
										for (int i=0;i<list22.size();i++)
										{
											Returns nreturn = ReturnList.get(list22.get(i));
											model.addRow(new Object[]{nreturn.BookId,nreturn.expd,nreturn.DOR});										
										}	
									
										JPanel jp33 = new JPanel();
										jp33.add(table);
										
										JButton b22 = new JButton("Pay Now");
										
										mainPanel1.setLayout(new GridLayout(10,1));
										mainPanel1.add(Hbutton);
										mainPanel1.add(jl1);
										mainPanel1.add(jl2);
										mainPanel1.add(table);
										JPanel p1 = new JPanel();
										p1.add(b22);
										mainPanel1.add(p1);
										
										frame11.setContentPane(mainPanel1);
										
										b22.addActionListener(new ActionListener()
										{
												public void actionPerformed(ActionEvent e)
												{
													for (int i=0;i<list22.size();i++)
													{
														Returns nreturn = ReturnList.get(list22.get(i));
														nreturn.paid = true;										
													}
												}
											});
									}
								}
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);
							}
						});
			}
		});

		JButton Button8 = new JButton("Edit User Details");
		Button8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Users nuser = new Users();
				
				frame.setVisible(false);
				
				JFrame frame1 = new JFrame(); // add user- 1st page frame
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame1.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JPanel dp = new JPanel();
				dp.setLayout(new GridLayout(5,1));
				
				//home button
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				//Hbutton.setPreferredSize(new Dimension(10, 10));
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				// add user -1st page components
				JLabel l1 = new JLabel("Enter Unique User ID:", SwingConstants.CENTER);
				JLabel l2 = new JLabel("Error: User Doesn't Exist.", SwingConstants.CENTER);
				JTextField txt1 = new JTextField(10);
				
				JButton b2 = new JButton("Edit User");
				b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String rNum = txt1.getText();
								JFrame frame11 = new JFrame(); // add user - 2nd page frame
								frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							    frame11.addWindowListener(new WindowAdapter() {
							        @Override
							        public void windowClosing(WindowEvent event) {
							            try {
											exitProcedure();
										} catch (IOException e) {
											e.printStackTrace();
										}
							        }
							    });
							    JPanel dp1 = new JPanel();
								
								if (!UserList.containsKey(rNum))
								{
									// add user - 2nd page if user exists
									dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l2);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);
								}
								else
								{
									Users temp1 = UserList.get(rNum);
									//add user - 2nd page of user doesn't exist
									JPanel n1 = new JPanel();
									JLabel l11 = new JLabel("Name");
									JTextField t1 = new JTextField(temp1.Name);
									n1.add(l11);
									n1.add(t1);
									
									JPanel n2 = new JPanel();
									JLabel l12 = new JLabel("Branch");
									JTextField t2 = new JTextField(temp1.Branch);
									n2.add(l12);
									n2.add(t2);
									
									JPanel n3 = new JPanel();
									JLabel l13 = new JLabel("Semester");
									JTextField t3 = new JTextField(temp1.Semester);
									n3.add(l13);
									n3.add(t3);
									
									JPanel n4 = new JPanel();
									JLabel l14 = new JLabel("Mobile:");
									JTextField t4 = new JTextField(temp1.Mob);
									n4.add(l14);
									n4.add(t4);
									
									JPanel n6 = new JPanel();
									JLabel l16 = new JLabel("No. of Books Issued: "+nuser.iBooks);
									n6.add(l16);
									
									JPanel n5 = new JPanel();
									JLabel l15 = new JLabel("Email: ");
									JTextField t5 = new JTextField(temp1.email);
									n5.add(l15);
									n5.add(t5);
									
									JButton nb1 = new JButton("Edit User");
									nb1.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													nuser.rNum = rNum;
													nuser.Name = t1.getText();
													nuser.Branch = t2.getText();
													nuser.Semester = t3.getText();
													nuser.Mob = t4.getText();
													nuser.email = t5.getText();
													UserList.put(rNum, nuser);
													
													JFrame f111 = new JFrame();
													f111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												    f111.addWindowListener(new WindowAdapter() {
												        @Override
												        public void windowClosing(WindowEvent event) {
												            try {
																exitProcedure();
															} catch (IOException e) {
																e.printStackTrace();
															}
												        }
												    });
													f111.getContentPane().add(BorderLayout.NORTH, Hbutton);
													JLabel lend = new JLabel("User details edited Successfully.");
													f111.getContentPane().add(lend);
													f111.setSize(400, 200);
													frame11.setVisible(false);
													f111.setVisible(true);
													
													
												}
											});
									
									dp1.setLayout(new BoxLayout(dp1, BoxLayout.Y_AXIS));
									
									dp1.add(Hbutton);
									dp1.add(n1);
									dp1.add(n2);
									dp1.add(n3);
									dp1.add(n4);
									dp1.add(n5);
									dp1.add(nb1);
									frame11.setContentPane(dp1);
									frame11.setSize(310,300);
								}
								frame11.setVisible(true);
								frame1.setVisible(false);
							}
						});			
				dp.add(Hbutton);
				dp.add(l1);
				dp.add(txt1);
				dp.add(b2);
				
				frame1.setContentPane(dp);
				frame1.setSize(300, 200);
				frame1.setVisible(true);
			}
		});

		JButton Button9 = new JButton("Edit Book Details");
		Button9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Books nuser = new Books();
				
				frame.setVisible(false);
				
				JFrame frame1 = new JFrame(); // add book- 1st page frame
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame1.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JPanel dp = new JPanel();
				dp.setLayout(new GridLayout(5,1));
				
				//home button
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				//Hbutton.setPreferredSize(new Dimension(10, 10));
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				// add book -1st page components
				JLabel l1 = new JLabel("Enter Unique Book ID:", SwingConstants.CENTER);
				JLabel l2 = new JLabel("Error: Book ID doesn't Exist.", SwingConstants.CENTER);
				JTextField txt1 = new JTextField(10);
				
				JButton b2 = new JButton("Edit Book");
				b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String rNum = txt1.getText();
								JFrame frame11 = new JFrame(); // add book - 2nd page frame
								frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							    frame11.addWindowListener(new WindowAdapter() {
							        @Override
							        public void windowClosing(WindowEvent event) {
							            try {
											exitProcedure();
										} catch (IOException e) {
											e.printStackTrace();
										}
							        }
							    });
							    JPanel dp1 = new JPanel();
								
								if (!BookList.containsKey(rNum))
								{
									// add book - 2nd page if user exists
									dp.add(Hbutton);
									dp.add(l1);
									dp.add(txt1);
									dp.add(b2);
									dp.add(l2);
									frame11.setContentPane(dp);
									frame11.setSize(310,200);	
								}
								else
								{
									Books temp1 = BookList.get(rNum);
									//add book - 2nd page of user doesn't exist
									JPanel n1 = new JPanel();
									JLabel l11 = new JLabel("ISBN");
									JTextField t1 = new JTextField(temp1.ISBN);
									n1.add(l11);
									n1.add(t1);
									
									JPanel n2 = new JPanel();
									JLabel l12 = new JLabel("Title");
									JTextField t2 = new JTextField(temp1.title);
									n2.add(l12);
									n2.add(t2);
									
									JPanel n3 = new JPanel();
									JLabel l13 = new JLabel("Authors");
									JTextField t3 = new JTextField(temp1.authors.get(0));
									//JTextArea t3 = new JTextArea(temp1.authors);
									JTextField t31;
									n3.add(l13);
									n3.add(t3);
									if (temp1.authors.size()>1)
									{
										t31 = new JTextField(temp1.authors.get(1));
										n3.add(t31);
									}
									
									JPanel n4 = new JPanel();
									JLabel l14 = new JLabel("Edition");
									JTextField t4 = new JTextField(""+temp1.edition);
									n4.add(l14);
									n4.add(t4);
									
									JPanel n5 = new JPanel();
									JLabel l15 = new JLabel("Field of Study");
									JTextField t5 = new JTextField(temp1.subject);
									n5.add(l15);
									n5.add(t5);
									
									JPanel n6 = new JPanel();
									JLabel l16 = new JLabel("Publisher");
									JTextField t6 = new JTextField(temp1.Publisher);
									n6.add(l16);
									n6.add(t6);
									
									JPanel n7 = new JPanel();
									JLabel l17 = new JLabel("Period of Issue");
									JTextField t7 = new JTextField(""+temp1.days);
									n7.add(l17);
									n7.add(t7);
									
									JButton nb1 = new JButton("Edit Book");
									nb1.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													nuser.BookId = rNum;
													nuser.ISBN = t1.getText();
													nuser.title = t2.getText();
													nuser.edition = Integer.parseInt(t4.getText());
													nuser.subject = t5.getText();
													nuser.Publisher = t6.getText();
													nuser.days = Integer.parseInt(t7.getText());
												
													String s[] = t3.getText().split("\\r?\\n");
												    ArrayList<String> arrList = new ArrayList<>(Arrays.asList(s));
												    nuser.authors = arrList;
												    
													BookList.put(rNum, nuser);
													
													JFrame f111 = new JFrame();
													f111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												    f111.addWindowListener(new WindowAdapter() {
												        @Override
												        public void windowClosing(WindowEvent event) {
												            try {
																exitProcedure();
															} catch (IOException e) {
																e.printStackTrace();
															}
												        }
												    });
													f111.getContentPane().add(BorderLayout.NORTH, Hbutton);
													JLabel lend = new JLabel("Book details edited successfully.");
													f111.getContentPane().add(lend);
													f111.setSize(400, 200);
													frame11.setVisible(false);
													f111.setVisible(true);
												}
											});
									
									dp1.setLayout(new GridLayout(9,1));
									
									dp1.add(Hbutton);
									dp1.add(n1);
									dp1.add(n2);
									dp1.add(n3);
									dp1.add(n4);
									dp1.add(n5);
									dp1.add(n6);
									dp1.add(n7);
									dp1.add(nb1);
									frame11.setContentPane(dp1);
									frame11.setSize(310,400);
								}
								frame11.setVisible(true);
								frame1.setVisible(false);
							}
						});			
				dp.add(Hbutton);
				dp.add(l1);
				dp.add(txt1);
				dp.add(b2);
				
				frame1.setContentPane(dp);
				frame1.setSize(300, 200);
				frame1.setVisible(true);
			}
		});

		JButton Button10 = new JButton("Remove a User");
		Button10.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new GridLayout(5,1));
				
				JPanel ep1 = new JPanel();
				JPanel hp1 = new JPanel();
				JPanel hp2 = new JPanel();
				JPanel hp3 = new JPanel();
				
				hp1.add(Hbutton);
				
				JLabel jl1 = new JLabel("Enter unqiue user id:", SwingConstants.CENTER);
				JTextField jt = new JTextField(20);
				hp2.add(jt);
				
				JButton jd = new JButton("Delete User");
				hp3.add(jd);
				
				mainPanel.add(hp1);
				mainPanel.add(ep1);
				mainPanel.add(jl1);
				mainPanel.add(hp2);
				mainPanel.add(hp3);
				
				frame11.setContentPane(mainPanel);
				frame11.setSize(600, 700);
				frame11.setVisible(true);
				
				jd.addActionListener(new ActionListener()
						{
					public void actionPerformed(ActionEvent e)
					{
						String rNum = jt.getText();
						if(rNum.isEmpty())
						{
							JPanel mainPanel1 = new JPanel();
							JLabel jp11 = new JLabel("The above field is mandatory.", SwingConstants.CENTER);
							mainPanel1.add(hp1);
							mainPanel1.add(ep1);
							mainPanel1.add(jl1);
							mainPanel1.add(hp2);
							mainPanel1.add(hp3);
							mainPanel1.add(jp11);
							mainPanel1.setLayout(new GridLayout(6,1));
							frame11.setContentPane(mainPanel1);
						}
						else if (!UserList.containsKey(rNum))
						{
							JPanel mainPanel1 = new JPanel();
							JLabel jp11 = new JLabel("Error: User doesn't exist.", SwingConstants.CENTER);
							mainPanel1.add(hp1);
							mainPanel1.add(ep1);
							mainPanel1.add(jl1);
							mainPanel1.add(hp2);
							mainPanel1.add(hp3);
							mainPanel1.add(jp11);
							mainPanel1.setLayout(new GridLayout(6,1));
							frame11.setContentPane(mainPanel1);
						}
						else
						{
							int count=0;
							list = new ArrayList<String>();
							ilist = new ArrayList<Issues>();
							for (int i=0;i<IssueList.size();i++)
							{
								Issues nIssue = IssueList.get(i);
								if (nIssue.rNum.equals(rNum))
								{
									count++;
									list.add(nIssue.BookId);
									ilist.add(nIssue);
								}
							}
							if (list.size()>0)
							{
								curr=1;
								total=count;
								cframe=frame11;
								JPanel jp = pageShow2(list, ilist, 1, count, frame, frame11);
								frame11.setContentPane(jp);
							}
							else
							{
								JPanel mainPanel1 = new JPanel();
								
								ArrayList<Integer> list22 = new ArrayList<Integer>();
								int fine=0;
								for(int i=0;i<ReturnList.size();i++)
								{
									Returns nreturn = ReturnList.get(i);
									if(nreturn.rNum.equals(rNum)&&!nreturn.paid)
									{
										list22.add(i);
										fine += nreturn.fine;
									}
								}
								if (fine==0)
								{
									JPanel mainPanel2 = new JPanel();
									mainPanel2.setLayout(new GridLayout(6,1));
									JLabel ll1 = new JLabel("User successfully deleted.");
									mainPanel2.add(hp1);
									mainPanel2.add(ep1);
									mainPanel2.add(jl1);
									mainPanel2.add(hp2);
									mainPanel2.add(hp3);
									mainPanel2.add(ll1);
									frame11.setContentPane(mainPanel2);
									UserList.remove(rNum);
								}
								else
								{
									JLabel jl1 = new JLabel("User has a pending fine of Rs. "+fine, SwingConstants.CENTER);
									JLabel jl2 = new JLabel("against the following:", SwingConstants.CENTER);
								
									DefaultTableModel model = new DefaultTableModel();
									JTable table = new JTable(model);
								
									model.addColumn("Book ID");
									model.addColumn("Expected D.O.R");
									model.addColumn("Actual D.O.R");
									
									model.addRow(new Object[]{"Book ID", "Expected D.O.R", "Actual D.O.R"});
									for (int i=0;i<list22.size();i++)
									{
										Returns nreturn = ReturnList.get(list22.get(i));
										model.addRow(new Object[]{nreturn.BookId,nreturn.expd,nreturn.DOR});										
									}	
								
									JPanel jp33 = new JPanel();
									jp33.add(table);
								
									JButton b22 = new JButton("Pay Now");
								
									mainPanel1.setLayout(new GridLayout(10,1));
									mainPanel1.add(Hbutton);
									mainPanel1.add(jl1);
									mainPanel1.add(jl2);
									mainPanel1.add(table);
									JPanel p1 = new JPanel();
									p1.add(b22);
									mainPanel1.add(p1);
									
									frame11.setContentPane(mainPanel1);
								}
							}
						}
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame11.setVisible(true);
					}
						});
			}
		});

		JButton Button11 = new JButton("Remove a Book");
		Button11.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						frame.setVisible(false);
						JFrame frame11 = new JFrame();
						frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					    frame11.addWindowListener(new WindowAdapter() {
					        @Override
					        public void windowClosing(WindowEvent event) {
					            try {
									exitProcedure();
								} catch (IOException e) {
									e.printStackTrace();
								}
					        }
					    });
						JButton Hbutton = new JButton();
						ImageIcon nicon = new ImageIcon("home2.jpg");
						Hbutton.setIcon(nicon);
						Hbutton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame.setVisible(true);
							}
						});
						
						JPanel mainPanel = new JPanel();
						mainPanel.setLayout(new GridLayout(5,1));
						
						JPanel ep1 = new JPanel();
						JPanel hp1 = new JPanel();
						JPanel hp2 = new JPanel();
						JPanel hp3 = new JPanel();
						
						hp1.add(Hbutton);
						
						JLabel jl1 = new JLabel("Enter unqiue Book id:", SwingConstants.CENTER);
						JTextField jt = new JTextField(20);
						hp2.add(jt);
						
						JButton jd = new JButton("Remove Book");
						hp3.add(jd);
						
						mainPanel.add(hp1);
						mainPanel.add(ep1);
						mainPanel.add(jl1);
						mainPanel.add(hp2);
						mainPanel.add(hp3);
						
						frame11.setContentPane(mainPanel);
						frame11.setSize(600, 500);
						frame11.setVisible(true);
						
						jd.addActionListener(new ActionListener()
								{
							public void actionPerformed(ActionEvent e)
							{
								String BookId = jt.getText();
								if(BookId.isEmpty())
								{
									JPanel mainPanel1 = new JPanel();
									JLabel jp11 = new JLabel("The above field is mandatory.", SwingConstants.CENTER);
									mainPanel1.add(hp1);
									mainPanel1.add(ep1);
									mainPanel1.add(jl1);
									mainPanel1.add(hp2);
									mainPanel1.add(hp3);
									mainPanel1.add(jp11);
									mainPanel1.setLayout(new GridLayout(6,1));
									frame11.setContentPane(mainPanel1);
								}
								else if (!BookList.containsKey(BookId))
								{
									JPanel mainPanel1 = new JPanel();
									JLabel jp11 = new JLabel("Error: Book Id doesn't exist.", SwingConstants.CENTER);
									mainPanel1.add(hp1);
									mainPanel1.add(ep1);
									mainPanel1.add(jl1);
									mainPanel1.add(hp2);
									mainPanel1.add(hp3);
									mainPanel1.add(jp11);
									mainPanel1.setLayout(new GridLayout(6,1));
									frame11.setContentPane(mainPanel1);
								}
								else
								{
									boolean flag = false;
									Issues temp = new Issues();
									for (int i=0;i<IssueList.size();i++)
									{
										Issues nIssue = IssueList.get(i);
										if (nIssue.BookId.equals(BookId))
										{
											flag=true;
											temp = nIssue;
											break;
										}
									}
									if (flag)
									{
										JPanel mp1 = new JPanel();
										JPanel ep1 = new JPanel();
										ep1.add(Hbutton);
										
										JLabel ll1 = new JLabel("Book is issued to the below mention student:", SwingConstants.CENTER);
										JLabel ll2 = new JLabel("Please return the book to the library first to delete it.", SwingConstants.CENTER);
										Users nuser = UserList.get(temp.rNum);
										JLabel name = new JLabel("Name: "+nuser.Name);
										JLabel roll = new JLabel("Roll Number: "+nuser.rNum);
										JLabel branch = new JLabel("Branch: "+nuser.Branch);
										JLabel doi = new JLabel("Date of Issue: "+temp.DOI);
										JLabel edor = new JLabel("Expected Date of Return: "+ceDOR(temp.DOI, BookList.get(BookId).days));
										JLabel mob = new JLabel("Mobile: "+nuser.Mob);
										
										mp1.setLayout(new GridLayout(10,1));
										
										mp1.add(ep1);
										mp1.add(ll1);
										mp1.add(name);
										mp1.add(roll);
										mp1.add(branch);
										mp1.add(doi);
										mp1.add(edor);
										mp1.add(mob);
										mp1.add(ll2);
										
										frame11.setContentPane(mp1);
										
									}
									else
									{
										JPanel mainPanel3 = new JPanel();
										mainPanel3.setLayout(new GridLayout(6,1));
										
										JLabel lb2 = new JLabel("Book successfully removed", SwingConstants.CENTER);
										mainPanel3.add(hp1);
										mainPanel3.add(ep1);
										mainPanel3.add(jl1);
										mainPanel3.add(hp2);
										mainPanel3.add(hp3);
										mainPanel3.add(lb2);
										frame11.setContentPane(mainPanel3);
										BookList.remove(BookId);
									}
								}
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);
							}
								});
					}
				});
		
		JButton Button12 = new JButton("Search Archive");
		Button4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				JFrame frame11 = new JFrame();
				frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    frame11.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent event) {
			            try {
							exitProcedure();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			    });
				JButton Hbutton = new JButton();
				ImageIcon nicon = new ImageIcon("home2.jpg");
				Hbutton.setIcon(nicon);
				Hbutton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for (Frame f : Frame.getFrames())
						{
							if (f.isVisible())
								f.setVisible(false);
						}
						frame.setVisible(true);
					}
				});
				
				JPanel mainPanel = new JPanel();
				JPanel ePanel = new JPanel();
				
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
			
				model.addColumn("Roll No.");
				model.addColumn("Book Id");
				model.addColumn("ISBN");
				model.addColumn("DOI");
				model.addColumn("DOR");
				model.addColumn("Fine");
				model.addColumn("Paid");
			
				model.addRow(new Object[]{"Roll No.", "Book Id", "ISBN", "DOI", "DOR", "Fine", "Paid"});
				
				for (int i=0;i<ReturnList.size();i++)
				{
					
				}
				
				JPanel title1 = new JPanel();
				JLabel bt = new JLabel("Book Title: ");
				JTextField jt = new JTextField(46);
				title1.add(bt);
				title1.add(jt);
				
				JPanel title2 = new JPanel();
				JLabel bt2 = new JLabel("Book Author: ");
				JTextField jt2 = new JTextField(44);
				title2.add(bt2);
				title2.add(jt2);
				
				JPanel title3 = new JPanel();
				JLabel bt3 = new JLabel("Book Author: ");
				JTextField jt3 = new JTextField(44);
				title3.add(bt3);
				title3.add(jt3);
				
				JPanel title4 = new JPanel();
				JLabel bt4 = new JLabel("Book Publisher: ");
				JTextField jt4 = new JTextField(42);
				title4.add(bt4);
				title4.add(jt4);
				
				JPanel title5 = new JPanel();
				JLabel bt5 = new JLabel("Book ISBN: ");
				JTextField jt5 = new JTextField(46);
				title5.add(bt5);
				title5.add(jt5);
				
				JPanel title6 = new JPanel();
				JLabel bt6 = new JLabel("Book ID: ");
				JTextField jt6 = new JTextField(47);
				title6.add(bt6);
				title6.add(jt6);
				
				JPanel s1 = new JPanel();
				JButton search = new JButton("Search");
				s1.add(search);
				
				mainPanel.setLayout(new GridLayout(9,1));
				
				mainPanel.add(Hbutton);
				mainPanel.add(ePanel);
				mainPanel.add(title1);
				mainPanel.add(title2);
				mainPanel.add(title3);
				mainPanel.add(title4);
				mainPanel.add(title5);
				mainPanel.add(title6);
				mainPanel.add(s1);
				
				frame11.setContentPane(mainPanel);
				frame11.setSize(600, 700);
				frame11.setVisible(true);
				
				search.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String title = jt.getText();
								String a1 = jt2.getText();
								String a2 = jt3.getText();
								String pub = jt4.getText();
								String isbn = jt5.getText();
								String id = jt6.getText();
								
								ArrayList<Books> book1 = new ArrayList<Books>();
								for (String key : BookList.keySet())
									book1.add(BookList.get(key));
								
								if (!title.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).title.equals(title))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!a1.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										Books nbook = book1.get(i);
										if (nbook.authors.get(0).equals(a1)||(nbook.authors.size()>1&&nbook.authors.get(1).equals(a1)))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!a2.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										Books nbook = book1.get(i);
										if (nbook.authors.get(0).equals(a2)||(nbook.authors.size()>1&&nbook.authors.get(1).equals(a2)))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!pub.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).Publisher.equals(pub))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!isbn.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).ISBN.equals(isbn))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								if (!id.isEmpty())
								{
									ArrayList<Books> book2 = new ArrayList<Books>();
									for (int i=0;i<book1.size();i++)
									{
										if (book1.get(i).BookId.equals(id))
											book2.add(book1.get(i));
									}
									book1=book2;
								}
								
								/*if (title.isEmpty()&&a1.isEmpty()&&a2.isEmpty()&&pub.isEmpty()&&isbn.isEmpty()&&id.isEmpty())
								{
									book3 = book1;
									curr=1;
									cframe=frame11;
									JPanel jp = searchShow(1, frame, frame11);
									frame11.setContentPane(jp);
									/*JLabel lb = new JLabel("Error: Please Enter at least one field", SwingConstants.CENTER);
									JPanel mainPanel1 = new JPanel();
									mainPanel1.setLayout(new GridLayout(10,1));
									
									mainPanel1.add(Hbutton);
									mainPanel1.add(ePanel);
									mainPanel1.add(title1);
									mainPanel1.add(title2);
									mainPanel1.add(title3);
									mainPanel1.add(title4);
									mainPanel1.add(title5);
									mainPanel1.add(title6);
									mainPanel1.add(s1);
									mainPanel1.add(lb);
									frame11.setContentPane(mainPanel1);
								}*/
								if (book1.size()==0)
								{
									JLabel lb = new JLabel("Sorry, No such book found", SwingConstants.CENTER);
									JPanel mainPanel1 = new JPanel();
									mainPanel1.setLayout(new GridLayout(10,1));
									
									mainPanel1.add(Hbutton);
									mainPanel1.add(ePanel);
									mainPanel1.add(title1);
									mainPanel1.add(title2);
									mainPanel1.add(title3);
									mainPanel1.add(title4);
									mainPanel1.add(title5);
									mainPanel1.add(title6);
									mainPanel1.add(s1);
									mainPanel1.add(lb);
									frame11.setContentPane(mainPanel1);
								}
								else
								{
									book3 = book1;
									curr=1;
									cframe=frame11;
									JPanel jp = searchShow(1, frame, frame11);
									frame11.setContentPane(jp);
									
								}
								for (Frame f : Frame.getFrames())
								{
									if (f.isVisible())
										f.setVisible(false);
								}
								frame11.setVisible(true);
							}
						});
			}
		});

		label = new JLabel("Select an option", SwingConstants.CENTER);
		JLabel dev = new JLabel("Developers: Rajat Tulasyan and Rohit Gohri", SwingConstants.CENTER);
		
		JPanel qp = new JPanel();
		qp.setLayout(new BorderLayout());
		JLabel uls = new JLabel("Total Users: "+UserList.size());
		JLabel bls = new JLabel("Total Books: "+BookList.size());
		qp.add(uls, BorderLayout.WEST);
		qp.add(bls, BorderLayout.EAST);
		
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(new GridLayout(15,1));

		drawPanel.add(label);
		
		drawPanel.add(Button1);
		drawPanel.add(Button2);
		drawPanel.add(Button3);
		drawPanel.add(Button4);
		drawPanel.add(Button5);
		drawPanel.add(Button6);
		drawPanel.add(Button7);
		drawPanel.add(Button8);
		drawPanel.add(Button9);
		drawPanel.add(Button10);
		drawPanel.add(Button11);
		//drawPanel.add(qp);
		JPanel ep122 = new JPanel();
		drawPanel.add(ep122);
		drawPanel.add(dev);
		
		
		
		frame.setContentPane(drawPanel);
		frame.setSize(600, 500);
		frame.setVisible(true);
		
		
		
	}
	
	public JPanel pageShow(ArrayList<String> list, ArrayList<Issues> ilist, int curr, int total, JFrame Home, JFrame cframe)
	{
		jj = new JPanel();
		Issues nIssue = ilist.get(curr-1);
		Books nBook = BookList.get(list.get(curr-1));
		
		JButton H1 = new JButton();
		ImageIcon nicon = new ImageIcon("home2.jpg");
		H1.setIcon(nicon);
		H1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Frame f : Frame.getFrames())
				{
					if (f.isVisible())
						f.setVisible(false);
				}
				Home.setVisible(true);
			}
		});
		
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		JLabel j12 = new JLabel("Book ID: "+ nBook.BookId);
		JLabel jl = new JLabel("Book Title: "+nBook.title);
		JLabel jl1 = new JLabel("Book Author: "+nBook.authors.get(0));
		JLabel jl2 = new JLabel();
		if (nBook.authors.size()>1)
			jl2 = new JLabel("Book Author: "+nBook.authors.get(1));
		JLabel jl3 = new JLabel("Date of Issue: "+nIssue.DOI);
		JLabel jl4 = new JLabel("Expected Date of Return: "+ceDOR(nIssue.DOI, nBook.days));
		JLabel jl5 = new JLabel("Showing page "+curr+" of "+total, SwingConstants.CENTER);
		
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		ImageIcon i1 = new ImageIcon("next.png");
		ImageIcon i2 = new ImageIcon("prev.png");
		jb1.setIcon(i1);
		jb2.setIcon(i2);
		
		jb1.addActionListener(new Listener1());
		jb2.addActionListener(new Listener2());
		
		jp.setLayout(new GridLayout(9,1));
		
		jp.add(H1);
		jp.add(jp1);
		jp.add(j12);
		jp.add(jl);
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		
		if (curr>1)
			jp2.add(jb2);
		jp2.add(jl5);
		if (curr<total)
			jp2.add(jb1);
		
		jp.add(jp2);
		
		return jp;
	}
	
	class Listener1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr += 1;
			jj=pageShow(list, ilist, curr, total, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);
		}		
	}
	
	class Listener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr -= 1;
			jj=pageShow(list, ilist, curr, total, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);		
		}		
	}
	
	public JPanel spageShow(int curr, JFrame Home, JFrame cframe)
	{
		int total = IssueList.size();
		jj = new JPanel();
		Issues nIssue = IssueList.get(curr-1);
		Books nBook = BookList.get(nIssue.BookId);
		Users nuser = UserList.get(nIssue.rNum);
		
		JButton H1 = new JButton();
		ImageIcon nicon = new ImageIcon("home2.jpg");
		H1.setIcon(nicon);
		H1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Frame f : Frame.getFrames())
				{
					if (f.isVisible())
						f.setVisible(false);
				}
				Home.setVisible(true);
			}
		});
		
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		JLabel j12 = new JLabel("Book ID: "+ nBook.BookId);
		JLabel jl = new JLabel("Book Title: "+nBook.title);
		JLabel j13 = new JLabel("Book ISBN: "+nBook.ISBN);
		JLabel jl1 = new JLabel("Book Author: "+nBook.authors.get(0));
		JLabel jl2 = new JLabel();
		if (nBook.authors.size()>1)
			jl2 = new JLabel("Book Author: "+nBook.authors.get(1));
		JLabel j16 = new JLabel("Book Issued to:", SwingConstants.CENTER);
		JLabel j14 = new JLabel("Name: "+nuser.Name);
		JLabel j15 = new JLabel("Roll No: "+nuser.rNum);
		JLabel jl3 = new JLabel("Date of Issue: "+nIssue.DOI);
		JLabel jl4 = new JLabel("Expected Date of Return: "+ceDOR(nIssue.DOI, nBook.days));
		JLabel jl5 = new JLabel("Showing page "+curr+" of "+total, SwingConstants.CENTER);
		
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		ImageIcon i1 = new ImageIcon("next.png");
		ImageIcon i2 = new ImageIcon("prev.png");
		jb1.setIcon(i1);
		jb2.setIcon(i2);
		
		jb1.addActionListener(new List1());
		jb2.addActionListener(new List2());
		
		jp.setLayout(new GridLayout(13,1));
		
		jp.add(H1);
		jp.add(jp1);
		jp.add(j12);
		jp.add(jl);
		jp.add(j13);
		jp.add(jl1);
		jp.add(jl2);
		jp.add(j16);
		jp.add(j14);
		jp.add(j15);
		jp.add(jl3);
		jp.add(jl4);
		
		if (curr>1)
			jp2.add(jb2);
		jp2.add(jl5);
		if (curr<total)
			jp2.add(jb1);
		
		jp.add(jp2);
		
		return jp;
	}
	
	class List1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr += 1;
			jj=spageShow(curr, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);
		}		
	}
	
	class List2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr -= 1;
			jj=spageShow(curr, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);		
		}		
	}

	public JPanel searchShow(int curr, JFrame Home, JFrame cframe)
	{
		int total = book3.size();
		jj = new JPanel();
		
		Books nbook = book3.get(curr-1);
		Issues nIssue = new Issues();
		int i;
		for (i=0;i<IssueList.size();i++)
		{
			Issues temp = IssueList.get(i);
			if (nbook.BookId.equals(temp.BookId))
			{
				nIssue = temp;
				break;
			}
		}
		boolean flag = false;
		if (i<IssueList.size())
			flag=true;
		Users nuser = UserList.get(nIssue.rNum);
		
		JButton H1 = new JButton();
		ImageIcon nicon = new ImageIcon("home2.jpg");
		H1.setIcon(nicon);
		H1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Frame f : Frame.getFrames())
				{
					if (f.isVisible())
						f.setVisible(false);
				}
				Home.setVisible(true);
			}
		});
		
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		JLabel j12 = new JLabel("Book ID: "+ nbook.BookId);
		JLabel jl = new JLabel("Book Title: "+nbook.title);
		JLabel j13 = new JLabel("Book ISBN: "+nbook.ISBN);
		JLabel jl1 = new JLabel("Book Author: "+nbook.authors.get(0));
		JLabel jl2 = new JLabel();
		if (nbook.authors.size()>1)
			jl2 = new JLabel("Book Author: "+nbook.authors.get(1));
		JLabel j16 = new JLabel("Book Issued to:", SwingConstants.CENTER);
		JLabel j14;
		JLabel j15;
		JLabel jl3;
		JLabel jl4;
		JLabel jl12 = new JLabel("Book Available for Issue");
		JLabel jl5 = new JLabel("Showing page "+curr+" of "+total, SwingConstants.CENTER);
		
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		ImageIcon i1 = new ImageIcon("next.png");
		ImageIcon i2 = new ImageIcon("prev.png");
		jb1.setIcon(i1);
		jb2.setIcon(i2);
		
		jb1.addActionListener(new next());
		jb2.addActionListener(new prev());
		
		jp.setLayout(new GridLayout(13,1));
		
		jp.add(H1);
		jp.add(jp1);
		jp.add(j12);
		jp.add(jl);
		jp.add(j13);
		jp.add(jl1);
		jp.add(jl2);
		if (flag) // if book issued
		{
			j14 = new JLabel("Name: "+nuser.Name);
			j15 = new JLabel("Roll No: "+nuser.rNum);
			jl3 = new JLabel("Date of Issue: "+nIssue.DOI);
			jl4 = new JLabel("Expected Date of Return: "+ceDOR(nIssue.DOI, nbook.days));
			jp.add(j16);
			jp.add(j14);
			jp.add(j15);
			jp.add(jl3);
			jp.add(jl4);
		}
		else // if book not issued
			jp.add(jl12);
		
		
		if (curr>1)
			jp2.add(jb2);
		jp2.add(jl5);
		if (curr<total)
			jp2.add(jb1);
		
		jp.add(jp2);
		
		return jp;
	}
	
	class next implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr += 1;
			jj=searchShow(curr, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);
		}		
	}
	
	class prev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr -= 1;
			jj=searchShow(curr, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);		
		}		
	}
	
	public JPanel pageShow2(ArrayList<String> list, ArrayList<Issues> ilist, int curr, int total, JFrame Home, JFrame cframe)
	{
		jj = new JPanel();
		Issues nIssue = ilist.get(curr-1);
		Books nBook = BookList.get(list.get(curr-1));
		
		JLabel jl111 = new JLabel("User has a pending Issue:", SwingConstants.CENTER);
		JButton H1 = new JButton();
		ImageIcon nicon = new ImageIcon("home2.jpg");
		H1.setIcon(nicon);
		H1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Frame f : Frame.getFrames())
				{
					if (f.isVisible())
						f.setVisible(false);
				}
				Home.setVisible(true);
			}
		});
		
		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		
		JLabel j12 = new JLabel("Book ID: "+ nBook.BookId);
		JLabel jl = new JLabel("Book Title: "+nBook.title);
		JLabel jl1 = new JLabel("Book Author: "+nBook.authors.get(0));
		JLabel jl2 = new JLabel();
		if (nBook.authors.size()>1)
			jl2 = new JLabel("Book Author: "+nBook.authors.get(1));
		JLabel jl3 = new JLabel("Date of Issue: "+nIssue.DOI);
		JLabel jl4 = new JLabel("Expected Date of Return: "+ceDOR(nIssue.DOI, nBook.days));
		JLabel jl5 = new JLabel("Showing page "+curr+" of "+total, SwingConstants.CENTER);
		
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		ImageIcon i1 = new ImageIcon("next.png");
		ImageIcon i2 = new ImageIcon("prev.png");
		jb1.setIcon(i1);
		jb2.setIcon(i2);
		
		jb1.addActionListener(new Lst1());
		jb2.addActionListener(new Lst2());
		JLabel jl11 = new JLabel("User cannot be deleted until all pending issues and dues are cleared.", SwingConstants.CENTER);
		jp.setLayout(new GridLayout(11,1));
		
		jp.add(H1);
		jp.add(jl111);
		jp.add(j12);
		jp.add(jl);
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		
		if (curr>1)
			jp2.add(jb2);
		jp2.add(jl5);
		if (curr<total)
			jp2.add(jb1);
		
		jp.add(jp2);
		jp.add(jl11);
		
		return jp;
	}
	
	class Lst1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr += 1;
			jj=pageShow2(list, ilist, curr, total, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);
		}		
	}
	
	class Lst2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame f : Frame.getFrames())
			{
				if (f.isVisible())
					f.setVisible(false);
			}
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    jf.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            try {
						exitProcedure();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			cframe.setVisible(false);
			curr -= 1;
			jj=pageShow2(list, ilist, curr, total, frame, cframe);	
			jf.setContentPane(jj);
			jf.setSize(600, 700);
			jf.setVisible(true);		
		}		
	}
	
	public String ceDOR(String date, int days)
	{
		StringBuilder sb = new StringBuilder();
		int month = Integer.parseInt(date.substring(3, 5));
		int day = Integer.parseInt(date.substring(0, 2));
		int year = Integer.parseInt(date.substring(6, 10));
		if (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		{
			if (day+days<=31)
			{
				sb.append(day+days);
				sb.append(date.substring(2,10));
				return sb.toString();
			}
			else
			{
				sb.append(day+days-31);
				sb.append("/");
				if(month<9)
					sb.append("0");
				sb.append(month+1);
				sb.append(date.substring(5, 10));
				return sb.toString();
			}
		}
		else if (month==4||month==6||month==9||month==11)
		{
			if (day+days<=30)
			{
				sb.append(day+days);
				sb.append(date.substring(2,10));
				return sb.toString();
			}
			else
			{
				sb.append(day+days-30);
				sb.append("/");
				if(month<9)
					sb.append("0");
				sb.append(month+1);
				sb.append(date.substring(5, 10));
				return sb.toString();
			}
		}
		else
		{
			if (year%4==0)
			{
				if (day+days<=29)
				{
					sb.append(day+days);
					sb.append(date.substring(2,10));
					return sb.toString();
				}
				else
				{
					sb.append(day+days-20);
					sb.append("/");
					sb.append("03/");
					sb.append(date.substring(6, 10));
					return sb.toString();
				}
			}
			else
			{
				if (day+days<=28)
				{
					sb.append(day+days);
					sb.append(date.substring(2,10));
					return sb.toString();
				}
				else
				{
					sb.append(day+days-28);
					sb.append("/");
					sb.append("03/");
					sb.append(date.substring(6, 10));
					return sb.toString();
				}
			}
		}
	}
	
	public static int calculate(String DOI, String DOR)
	{
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
		    Date date1 = myFormat.parse(DOI);
		    Date date2 = myFormat.parse(DOR);
		    long diff = date2.getTime() - date1.getTime();
		    //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return 0;
		
	}
	
	public void exitProcedure() throws IOException
	{
		PrintWriter pw = new PrintWriter("Books.txt");
		pw.close();
		PrintWriter pw1 = new PrintWriter("Issues.txt");
		pw1.close();
		PrintWriter pw2 = new PrintWriter("Users.txt");
		pw2.close();
		PrintWriter pw3 = new PrintWriter("Returns.txt");
		pw3.close();
		OutputStream fos = null;
	    try {
	        fos = new FileOutputStream("Books.txt");
	        for ( String key : BookList.keySet())
	        {
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(BookList.get(key));
	        }
	        
	    } finally {
	        if (fos != null)
	            fos.close();
	    }
	    try {
	        fos = new FileOutputStream("Users.txt");
	        for ( String key : UserList.keySet())
	        {
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(UserList.get(key));
	        }
	        
	    } finally {
	        if (fos != null)
	            fos.close();
	    }
	    try {
	        fos = new FileOutputStream("Issues.txt");
	        //ObjectOutputStream oos = new ObjectOutputStream(fos);
	        //oos.writeObject(IssueList);
	        for (int i=0;i<IssueList.size();i++)
	        {
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(IssueList.get(i));
	        }
	        
	    } finally {
	        if (fos != null)
	            fos.close();
	    }
	    
	    try {
	        fos = new FileOutputStream("Returns.txt");
	        for (int i=0;i<ReturnList.size();i++)
	        {
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(ReturnList.get(i));
	        }
	        
	    } finally {
	        if (fos != null)
	            fos.close();
	    }
	}
	
	

}
