import java.util.*;

public class Sudoku {
    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("WELCOME! Who is playing?");
        String player1 = keyboard.nextLine();


        //Declare matrix and initialize spaces with "" values
        String[][] matrix = new String[9][9];
        String[] sumsMatrix = new String[27];


        for (int rows = 0; rows <= 8; rows++) {
            for (int cols = 0; cols <= 8; cols++) {
                matrix[rows][cols] = "*";
            }
        }

        //Display board by calling function
        displayBoard(matrix);

        System.out.println("Ready to play Sudoku " + player1 + "?");

        boolean full, repeat;
        full = false;
        String pRow, pCol;
        int rowInt, colInt;

        String sumRow1 = null;
        String sumRow2 = null;
        String sumRow3 = null;
        String sumRow4 = null;
        String sumRow5 = null;
        String sumRow6 = null;
        String sumRow7 = null;
        String sumRow8 = null;
        String sumRow9 = null;

        String sumCol1 = null;
        String sumCol2 = null;
        String sumCol3 = null;
        String sumCol4 = null;
        String sumCol5 = null;
        String sumCol6 = null;
        String sumCol7 = null;
        String sumCol8 = null;
        String sumCol9 = null;

        String sumBox1 = null;
        String sumBox2 = null; 
        String sumBox3 = null; 
        String sumBox4 = null; 
        String sumBox5 = null; 
        String sumBox6 = null; 
        String sumBox7 = null; 
        String sumBox8 = null; 
        String sumBox9 = null;

        while (!full) {

            //Select spot to play
            System.out.println(player1 + ", please select a spot: ");

            //Row select
            System.out.println("Choose a Row to play on with 1-9.");
            pRow = keyboard.nextLine();

            //Input Validation
            while (pRow.trim().length() != 1) {
                System.out.println("ERROR: Please enter only one character.");
                pRow = keyboard.nextLine();
            }
            while (pRow.charAt(0) <= 48 || pRow.charAt(0) >= 58) {
                System.out.println("ERROR: Please enter a number 1-9.");
                pRow = keyboard.nextLine();
                while (pRow.trim().length() != 1) {
                    System.out.println("ERROR: Please enter only one character.");
                    pRow = keyboard.nextLine();
                }
            }

            //Column select
            System.out.println("Choose a Column to play on with 1-9.");
            pCol = keyboard.nextLine();

            //Input Validation
            while (pCol.trim().length() != 1) {
                System.out.println("ERROR: Please enter only one character.");
                pCol = keyboard.nextLine();
            }
            while (pCol.charAt(0) <= 48 || pCol.charAt(0) >= 58) {
                System.out.println("ERROR: Please enter a number 1-9.");
                pCol = keyboard.nextLine();
                while (pCol.trim().length() != 1) {
                    System.out.println("ERROR: Please enter only one character.");
                    pCol = keyboard.nextLine();
                }
            }

            //Convert String to Integer
            rowInt = Integer.parseInt(pRow);
            colInt = Integer.parseInt(pCol);

            //Select number to play in spot
            System.out.println("What number do you want to place in: " + pRow + ", " + pCol + "?");
            matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();

            //Input Validation
            while (matrix[(rowInt - 1)][(colInt - 1)].trim().length() != 1) {
                System.out.println("ERROR: Please enter only one character.");
                matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
            }
            while (matrix[(rowInt - 1)][(colInt - 1)].charAt(0) <= 48 || matrix[(rowInt - 1)][(colInt - 1)].charAt(0) >= 58) {
                System.out.println("ERROR: Please enter a number 1-9.");
                matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
                while (matrix[(rowInt - 1)][(colInt - 1)].trim().length() != 1) {
                    System.out.println("ERROR: Please enter only one character.");
                    matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
                }
            }


            //Display new board
            displayBoard(matrix);

            //Send matrix to Sums method and return a new matrix
            sumsMatrix = sums(matrix);

            //Sum all Rows
            sumRow1 = sumsMatrix[0];
            sumRow2 = sumsMatrix[1];
            sumRow3 = sumsMatrix[2];
            sumRow4 = sumsMatrix[3];
            sumRow5 = sumsMatrix[4];
            sumRow6 = sumsMatrix[5];
            sumRow7 = sumsMatrix[6];
            sumRow8 = sumsMatrix[7];
            sumRow9 = sumsMatrix[8];

            //Sum all Columns
            sumCol1 = sumsMatrix[9];
            sumCol2 = sumsMatrix[10];
            sumCol3 = sumsMatrix[11];
            sumCol4 = sumsMatrix[12];
            sumCol5 = sumsMatrix[13];
            sumCol6 = sumsMatrix[14];
            sumCol7 = sumsMatrix[15];
            sumCol8 = sumsMatrix[16];
            sumCol9 = sumsMatrix[17];

            //Sum all boxes
			sumBox1 = sumsMatrix[18];
			sumBox2 = sumsMatrix[19];
			sumBox3 = sumsMatrix[20];
			sumBox4 = sumsMatrix[21];
			sumBox5 = sumsMatrix[22];
			sumBox6 = sumsMatrix[23];
			sumBox7 = sumsMatrix[24];
			sumBox8 = sumsMatrix[25];
			sumBox9 = sumsMatrix[26];

            full = fullRow(sumRow1, sumRow2, sumRow3, sumRow4, sumRow5, sumRow6, sumRow7, sumRow8, sumRow9);
            if (full) {
                full = fullCol(sumCol1, sumCol2, sumCol3, sumCol4, sumCol5, sumCol6, sumCol7, sumCol8, sumCol9);
            }
        }

        System.out.println("The board is now full.");
        System.out.println(" ");
        System.out.println("Let's check for duplicate numbers.");

        //Sending each sum to the repeatNum method to check numbers in the string.
        repeat = repeatNum(sumRow1);
        if (!repeat){
            repeat = repeatNum(sumRow2);
            if(!repeat) {
                repeat = repeatNum(sumRow3);
                if(!repeat){
                    repeat = repeatNum(sumRow4);
                    if(!repeat){
                        repeat = repeatNum(sumRow5);
                        if(!repeat){
                            repeat = repeatNum(sumRow6);
                            if(!repeat){
                                repeat = repeatNum(sumRow7);
                                if(!repeat){
                                    repeat = repeatNum(sumRow8);
                                    if(!repeat){
                                        repeat = repeatNum(sumRow9);
                                        if(!repeat){
                                            System.out.println("The rows contain no duplicate numbers!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        repeat = repeatNum(sumCol1);
        if(!repeat){
            repeat = repeatNum(sumCol2);
            if(!repeat){
                repeat = repeatNum(sumCol3);
                if(!repeat){
                    repeat = repeatNum(sumCol4);
                    if(!repeat){
                        repeat = repeatNum(sumCol5);
                        if(!repeat){
                            repeat = repeatNum(sumCol6);
                            if(!repeat){
                                repeat = repeatNum(sumCol7);
                                if(!repeat){
                                    repeat = repeatNum(sumCol8);
                                    if(!repeat){
                                        repeat = repeatNum(sumCol9);
                                        if(!repeat){
                                            System.out.println("The columns contain no duplicate numbers!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        repeat = repeatNum(sumBox1);
        if(!repeat){
            repeat = repeatNum(sumBox2);
            if(!repeat){
                repeat = repeatNum(sumBox3);
                if(!repeat){
                    repeat = repeatNum(sumBox4);
                    if(!repeat){
                        repeat = repeatNum(sumBox5);
                        if(!repeat){
                            repeat = repeatNum(sumBox6);
                            if(!repeat){
                                repeat = repeatNum(sumBox7);
                                if(!repeat){
                                    repeat = repeatNum(sumBox8);
                                    if(!repeat){
                                        repeat = repeatNum(sumBox9);
                                        if(!repeat){
                                            System.out.println("The squares contain no duplicate numbers!");
                                            System.out.println("CONGRATULATIONS, YOU WIN!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //If there is a repeating number, let user know game is not over and repeat.
        while (repeat) {
            System.out.println("We have a duplicate number in either a row, column or box.");
            displayBoard(matrix);
            System.out.println("If you wish to beat the game, look through the board for the error.");
            
            System.out.println("Press Y to continue OR any other character to end:");
            char firstChar;
            String input = "No";
            input = keyboard.nextLine();
            while(input.trim().length() <= 0){
                System.out.println("ERROR: Please enter a character:");
                input = keyboard.nextLine();
            }
            input = input.toUpperCase();
            firstChar = input.charAt(0);
            if(firstChar == 'Y'){
                System.out.println(player1 + ", please select a spot: ");
	
				System.out.println("Choose a Row to play on with 1-9:");
				pRow = keyboard.nextLine();
	
				while (pRow.trim().length() != 1) {
					System.out.println("ERROR: Please enter only one character:");
					pRow = keyboard.nextLine();
				}
				
				while (pRow.charAt(0) <= 48 || pRow.charAt(0) >= 58) {
					System.out.println("ERROR: Please enter a number 1-9:");
					pRow = keyboard.nextLine();
					while (pRow.trim().length() != 1) {
						System.out.println("ERROR: Please enter only one character:");
						pRow = keyboard.nextLine();
					}
				}
	
				System.out.println("Choose a Column to play on with 1-9:");
				pCol = keyboard.nextLine();
	
				while (pCol.trim().length() != 1) {
					System.out.println("ERROR: Please enter only one character:");
					pCol = keyboard.nextLine();
				}
	
				while (pCol.charAt(0) <= 48 || pCol.charAt(0) >= 58) {
					System.out.println("ERROR: Please enter a number 1-9:");
					pCol = keyboard.nextLine();
					while (pCol.trim().length() != 1) {
						System.out.println("ERROR: Please enter only one character:");
						pCol = keyboard.nextLine();
					}
				}
	
	
				rowInt = Integer.parseInt(pRow);
				colInt = Integer.parseInt(pCol);
	
				System.out.println("What number do you want to place in: " + pRow + ", " + pCol + "?");
				matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
	
				while (matrix[(rowInt - 1)][(colInt - 1)].trim().length() != 1) {
					System.out.println("ERROR: Please enter only one character:");
					matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
				}
	
				while (matrix[(rowInt - 1)][(colInt - 1)].charAt(0) <= 48 || matrix[(rowInt - 1)][(colInt - 1)].charAt(0) >= 58) {
					System.out.println("ERROR: Please enter a number 1-9:");
					matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
					while (matrix[(rowInt - 1)][(colInt - 1)].trim().length() != 1) {
						System.out.println("ERROR: Please enter only one character:");
						matrix[(rowInt - 1)][(colInt - 1)] = keyboard.nextLine();
					}
				}
	
				sumsMatrix = sums(matrix);
	
				sumRow1 = sumsMatrix[0];
				sumRow2 = sumsMatrix[1];
				sumRow3 = sumsMatrix[2];
				sumRow4 = sumsMatrix[3];
				sumRow5 = sumsMatrix[4];
				sumRow6 = sumsMatrix[5];
				sumRow7 = sumsMatrix[6];
				sumRow8 = sumsMatrix[7];
				sumRow9 = sumsMatrix[8];
				sumCol1 = sumsMatrix[9];
				sumCol2 = sumsMatrix[10];
				sumCol3 = sumsMatrix[11];
				sumCol4 = sumsMatrix[12];
				sumCol5 = sumsMatrix[13];
				sumCol6 = sumsMatrix[14];
				sumCol7 = sumsMatrix[15];
				sumCol8 = sumsMatrix[16];
				sumCol9 = sumsMatrix[17];
				sumBox1 = sumsMatrix[18];
				sumBox2 = sumsMatrix[19];
				sumBox3 = sumsMatrix[20];
				sumBox4 = sumsMatrix[21];
				sumBox5 = sumsMatrix[22];
				sumBox6 = sumsMatrix[23];
				sumBox7 = sumsMatrix[24];
				sumBox8 = sumsMatrix[25];
				sumBox9 = sumsMatrix[26];
	
				repeat = repeatNum(sumRow1);
				if (!repeat){
					repeat = repeatNum(sumRow2);
					if(!repeat) {
						repeat = repeatNum(sumRow3);
						if(!repeat){
							repeat = repeatNum(sumRow4);
							if(!repeat){
								repeat = repeatNum(sumRow5);
								if(!repeat){
									repeat = repeatNum(sumRow6);
									if(!repeat){
										repeat = repeatNum(sumRow7);
										if(!repeat){
											repeat = repeatNum(sumRow8);
											if(!repeat){
												repeat = repeatNum(sumRow9);
												if(!repeat){
													System.out.println("The rows contain no duplicate numbers!");
												}
											}
										}
									}
								}
							}
						}
					}
				}
		
				repeat = repeatNum(sumCol1);
				if(!repeat){
					repeat = repeatNum(sumCol2);
					if(!repeat){
						repeat = repeatNum(sumCol3);
						if(!repeat){
							repeat = repeatNum(sumCol4);
							if(!repeat){
								repeat = repeatNum(sumCol5);
								if(!repeat){
									repeat = repeatNum(sumCol6);
									if(!repeat){
										repeat = repeatNum(sumCol7);
										if(!repeat){
											repeat = repeatNum(sumCol8);
											if(!repeat){
												repeat = repeatNum(sumCol9);
												if(!repeat){
													System.out.println("The columns contain no duplicate numbers!");
												}
											}
										}
									}
								}
							}
						}
					}
				}
		
				repeat = repeatNum(sumBox1);
		
				if(!repeat){
					repeat = repeatNum(sumBox2);
					if(!repeat){
						repeat = repeatNum(sumBox3);
						if(!repeat){
							repeat = repeatNum(sumBox4);
							if(!repeat){
								repeat = repeatNum(sumBox5);
								if(!repeat){
									repeat = repeatNum(sumBox6);
									if(!repeat){
										repeat = repeatNum(sumBox7);
										if(!repeat){
											repeat = repeatNum(sumBox8);
											if(!repeat){
												repeat = repeatNum(sumBox9);
												if(!repeat){
													System.out.println("The squares contain no duplicate numbers!");
													System.out.println("CONGRATULATIONS, YOU WIN!");
												}
											}
										}
									}
								}
							}
						}
					}
				}
            } else {
                repeat = false;
                System.out.println("You did not finish the board. Thanks for playing!");
            }

            
        }
    }

    public static void displayBoard(String[][] matrix) {
        //Display board to the player

        //Label columns
        for (int k = 0; k <= 8; k++) {
            if (k == 0 || k == 8) {
                if (k == 0) {
                    System.out.print("COLUMN   1   ");
                } else {
                    System.out.println("  " + (k+1));
                }
            } else {
                if (k == 6) {
                    System.out.print("    " + (k + 1) + " ");
                } else {
                    if (k <= 2) {
                        System.out.print(k + 1 + "   ");
                    } else {
                        System.out.print("  " + (k + 1) + " ");
                    }
                }
            }
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }


        int j = 0;
        while (j == 0) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 1 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 1) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 2 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 2) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 3 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 3) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 4 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 4) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 5 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 5) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 6 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 6) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 7 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 7) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 8 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }

        while (j == 8) {
            for (int cols = 0; cols <= 8; cols++) {
                while (cols != 2 && cols != 5 && cols != 8 && cols != 0) {
                    System.out.print(matrix[j][cols] + " | ");
                    cols = cols + 1;
                }
                if (cols == 8 || cols ==0) {
                    if (cols == 0) {
                        System.out.print("ROW 9 || " + matrix[j][cols] + " | ");
                    } else {
                        System.out.println(matrix[j][cols] + " || ");
                    }
                } else {
                    System.out.print(matrix[j][cols] + " | | ");
                }
            }
            j = j + 1;
        }

        //For ROW break
        for (int i = 0; i <= 8; i++) {
            while (i != 8) {
                if (i == 0) {
                    System.out.print("      +-----");
                } else {
                    System.out.print("----");
                }

                i = i + 1;
            }
            System.out.println("--------+");
        }
    }

    public static String[] sums(String[][] matrix){
        String[] sumMatrix = new String[27];
        
        //Sum all Rows
        sumMatrix[0] = matrix[0][0] + matrix[0][1] + matrix[0][2] + matrix[0][3] + matrix[0][4] + matrix[0][5] + matrix[0][6] + matrix[0][7] + matrix[0][8];
        sumMatrix[1] = matrix[1][0] + matrix[1][1] + matrix[1][2] + matrix[1][3] + matrix[1][4] + matrix[1][5] + matrix[1][6] + matrix[1][7] + matrix[1][8];
        sumMatrix[2] = matrix[2][0] + matrix[2][1] + matrix[2][2] + matrix[2][3] + matrix[2][4] + matrix[2][5] + matrix[2][6] + matrix[2][7] + matrix[2][8];
        sumMatrix[3] = matrix[3][0] + matrix[3][1] + matrix[3][2] + matrix[3][3] + matrix[3][4] + matrix[3][5] + matrix[3][6] + matrix[3][7] + matrix[3][8];
        sumMatrix[4] = matrix[4][0] + matrix[4][1] + matrix[4][2] + matrix[4][3] + matrix[4][4] + matrix[4][5] + matrix[4][6] + matrix[4][7] + matrix[4][8];
        sumMatrix[5] = matrix[5][0] + matrix[5][1] + matrix[5][2] + matrix[5][3] + matrix[5][4] + matrix[5][5] + matrix[5][6] + matrix[5][7] + matrix[5][8];
        sumMatrix[6] = matrix[6][0] + matrix[6][1] + matrix[6][2] + matrix[6][3] + matrix[6][4] + matrix[6][5] + matrix[6][6] + matrix[6][7] + matrix[6][8];
        sumMatrix[7] = matrix[7][0] + matrix[7][1] + matrix[7][2] + matrix[7][3] + matrix[7][4] + matrix[7][5] + matrix[7][6] + matrix[7][7] + matrix[7][8];
        sumMatrix[8] = matrix[8][0] + matrix[8][1] + matrix[8][2] + matrix[8][3] + matrix[8][4] + matrix[8][5] + matrix[8][6] + matrix[8][7] + matrix[8][8];

        //Sum all Columns
        sumMatrix[9] = matrix[0][0] + matrix[1][0] + matrix[2][0] + matrix[3][0] + matrix[4][0] + matrix[5][0] + matrix[6][0] + matrix[7][0] + matrix[8][0];
        sumMatrix[10] = matrix[0][1] + matrix[1][1] + matrix[2][1] + matrix[3][1] + matrix[4][1] + matrix[5][1] + matrix[6][1] + matrix[7][1] + matrix[8][1];
        sumMatrix[11] = matrix[0][2] + matrix[1][2] + matrix[2][2] + matrix[3][2] + matrix[4][2] + matrix[5][2] + matrix[6][2] + matrix[7][2] + matrix[8][2];
        sumMatrix[12] = matrix[0][3] + matrix[1][3] + matrix[2][3] + matrix[3][3] + matrix[4][3] + matrix[5][3] + matrix[6][3] + matrix[7][3] + matrix[8][3];
        sumMatrix[13] = matrix[0][4] + matrix[1][4] + matrix[2][4] + matrix[3][4] + matrix[4][4] + matrix[5][4] + matrix[6][4] + matrix[7][4] + matrix[8][4];
        sumMatrix[14] = matrix[0][5] + matrix[1][5] + matrix[2][5] + matrix[3][5] + matrix[4][5] + matrix[5][5] + matrix[6][5] + matrix[7][5] + matrix[8][5];
        sumMatrix[15] = matrix[0][6] + matrix[1][6] + matrix[2][6] + matrix[3][6] + matrix[4][6] + matrix[5][6] + matrix[6][6] + matrix[7][6] + matrix[8][6];
        sumMatrix[16] = matrix[0][7] + matrix[1][7] + matrix[2][7] + matrix[3][7] + matrix[4][7] + matrix[5][7] + matrix[6][7] + matrix[7][7] + matrix[8][7];
        sumMatrix[17] = matrix[0][8] + matrix[1][8] + matrix[2][8] + matrix[3][8] + matrix[4][8] + matrix[5][8] + matrix[6][8] + matrix[7][8] + matrix[8][8];

        //Sum all boxes
        sumMatrix[18] = matrix[0][0] + matrix[1][0] + matrix[2][0] + matrix[0][1] + matrix[1][1] + matrix[2][1] + matrix[0][2] + matrix[1][2] + matrix[2][2];
        sumMatrix[19] = matrix[0][3] + matrix[1][3] + matrix[2][3] + matrix[0][4] + matrix[1][4] + matrix[2][4] + matrix[0][5] + matrix[1][5] + matrix[2][5];
		sumMatrix[20] = matrix[0][6] + matrix[1][6] + matrix[2][6] + matrix[0][7] + matrix[1][7] + matrix[2][7] + matrix[0][8] + matrix[1][8] + matrix[2][8];
		sumMatrix[21] = matrix[3][0] + matrix[4][0] + matrix[5][0] + matrix[3][1] + matrix[4][1] + matrix[5][1] + matrix[3][2] + matrix[4][2] + matrix[5][2];
		sumMatrix[22] = matrix[3][3] + matrix[4][3] + matrix[5][3] + matrix[3][4] + matrix[4][4] + matrix[5][4] + matrix[3][5] + matrix[4][5] + matrix[5][5];
		sumMatrix[23] = matrix[3][6] + matrix[4][6] + matrix[5][6] + matrix[3][7] + matrix[4][7] + matrix[5][7] + matrix[3][8] + matrix[4][8] + matrix[5][8];
		sumMatrix[24] = matrix[6][0] + matrix[7][0] + matrix[8][0] + matrix[6][1] + matrix[7][1] + matrix[8][1] + matrix[6][2] + matrix[7][2] + matrix[8][2];
		sumMatrix[25] = matrix[6][3] + matrix[7][3] + matrix[8][3] + matrix[6][4] + matrix[7][4] + matrix[8][4] + matrix[6][5] + matrix[7][5] + matrix[8][5];
		sumMatrix[26] = matrix[6][6] + matrix[7][6] + matrix[8][6] + matrix[6][7] + matrix[7][7] + matrix[8][7] + matrix[6][8] + matrix[7][8] + matrix[8][8];
        
        return sumMatrix;
    }

    public static boolean fullRow (String sumR1, String sumR2, String sumR3, String sumR4, String sumR5, String sumR6, String sumR7, String sumR8, String sumR9) {
        boolean full;
        full = false;
        if (!sumR1.contains("*")) {
            if (!sumR2.contains("*")) {
                if (!sumR3.contains("*")) {
                    if (!sumR4.contains("*")) {
                        if (!sumR5.contains("*")) {
                            if (!sumR6.contains("*")) {
                                if (!sumR7.contains("*")) {
                                    if (!sumR8.contains("*")) {
                                        if (!sumR9.contains("*")) {
                                            full = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return full;
    }

    public static boolean fullCol (String sumC1, String sumC2, String sumC3, String sumC4, String sumC5, String sumC6, String sumC7, String sumC8, String sumC9) {
        boolean full;
        full = false;
        if (!sumC1.contains("*")) {
            if (!sumC2.contains("*")) {
                if (!sumC3.contains("*")) {
                    if (!sumC4.contains("*")) {
                        if (!sumC5.contains("*")) {
                            if (!sumC6.contains("*")) {
                                if (!sumC7.contains("*")) {
                                    if (!sumC8.contains("*")) {
                                        if (!sumC9.contains("*")) {
                                            full = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return full;
    }

    public static boolean repeatNum(String sum) {
        boolean repeat = false;

        if(!(sum.lastIndexOf("1") == sum.indexOf("1"))){
            repeat = true;
        } else {
            if(!(sum.lastIndexOf("2") == sum.indexOf("2"))) {
                repeat = true;
            } else {
                if(!(sum.lastIndexOf("3") == sum.indexOf("3"))) {
                    repeat = true;
                } else {
                    if(!(sum.lastIndexOf("4") == sum.indexOf("4"))){
                        repeat = true;
                    } else {
                        if(!(sum.lastIndexOf("5") == sum.indexOf("5"))) {
                            repeat = true;
                        } else {
                            if(!(sum.lastIndexOf("6") == sum.indexOf("6"))) {
                                repeat = true;
                            } else {
                                if(!(sum.lastIndexOf("7") == sum.indexOf("7"))){
                                    repeat = true;
                                } else {
                                    if(!(sum.lastIndexOf("8") == sum.indexOf("8"))) {
                                        repeat = true;
                                    } else {
                                        if(!(sum.lastIndexOf("9") == sum.indexOf("9"))) {
                                            repeat = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return repeat;
    }
}