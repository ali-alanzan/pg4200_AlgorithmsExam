package pg4200.algorithms.exam;

import pg4200.algorithms.exam.ex05.BitReader;
import pg4200.algorithms.exam.ex05.BitWriter;


public class Ex05 {

    public static byte[] compress(String move){


        BitWriter writer = new BitWriter();
        /*
            Essential that we write the length of the sequence.
            Otherwise, the 00 in the last byte would be undefined,
            ie they could be just padding or actual T values.

            As a byte has 8 bits, and we use 2 bits per letter, each
            byte can contain 4 letters.
         */
        writer.write(move.length());

        for(int i=0; i<move.length(); i++){
            char c = move.charAt(i);
            boolean isSquareLetter = false;
            /**
             *
             * Using 7 bits per letter
             *
             * Writing first 3 bytes for the first char(s) of those words (pawn, rook, knight, bishop, queen, King)
                        'prkbqK' in sorted order 'Kbkpqr':
                    for 'K'  ---write 100
             *  if c is number    ---write 011 at first
             *  if c is !   write 010 0001
             *  if c is p   write
             * then write 4 bits as base and the remaining bits will be added in the code
             *
             */



            /**
             * any letter in those letter ('k', 'b') write 110
             */
            if(c == 'b' || c == 'k') { //110
                writer.write(true);
                writer.write(true);
                writer.write(false);
            }
            /**
             * any letter in those letter ('p', 'q', 'r') write 111
             */
            if(c == 'p' || c == 'q' || c == 'r') { //111
                writer.write(true);
                writer.write(true);
                writer.write(true);
            }
            /**
             * For any number write 011 in first
             */
            if(String.valueOf(c).matches("[0-9]")) { //011
                writer.write(false);
                writer.write(true);
                writer.write(true);
            }

            /**
             * For the square Any letter from A-H write 100
             * i % 2 == 1
             * 0 and 1 for the number and the piece
             * then when it equals 1 it's in 3 and that is a square
             *  will be stored only with Capital alphabet to avoiding conflict
             *   write 100
             */
            if(i >= 2 && ( i % 2 == 0 ||  i % 4 == 0  ) && String.valueOf(c).toUpperCase().matches("[A-H]")  ) { // [A-H] 100
                writer.write(true);
                writer.write(false);
                writer.write(false);

                isSquareLetter = true;
            }


            /**
             * write the letter is byte
             *  every letter take 3 bits in the beginning then 4 bits based on it's type
             */

            if(c == '1') { // 0001

                writer.write(false);
                writer.write(false);
                writer.write(false);
                writer.write(true);
            } else if(c == '2') { // 0010
                writer.write(false);
                writer.write(false);
                writer.write(true);
                writer.write(false);
            } else if(c== '3'){  // 0011
                writer.write(false);
                writer.write(false);
                writer.write(true);
                writer.write(true);
            } else if(c== '4'){ // 0100
                writer.write(false);
                writer.write(true);
                writer.write(false);
                writer.write(false);
            }else if(c== '5'){ // 0101
                writer.write(false);
                writer.write(true);
                writer.write(false);
                writer.write(true);
            }else if(c== '6'){ // 0110
                writer.write(false);
                writer.write(true);
                writer.write(true);
                writer.write(false);
            } else if(c== '7'){ //0111
                writer.write(false);
                writer.write(true);
                writer.write(true);
                writer.write(true);
            }else if(c== '8'){ //1000
                writer.write(true);
                writer.write(false);
                writer.write(false);
                writer.write(false);
            }else if(c== '9'){ //1001
                writer.write(true);
                writer.write(false);
                writer.write(false);
                writer.write(true);
            } else if(c== 'r'){ //0010
                writer.write(false);
                writer.write(false);
                writer.write(true);
                writer.write(false);
            }  else if(c== 'k'){ //1011
                writer.write(true);
                writer.write(false);
                writer.write(true);
                writer.write(true);
            } else if(c== 'b'){ //0010
                writer.write(false);
                writer.write(false);
                writer.write(true);
                writer.write(false);
            } else if( c == 'p') { // 0000
                writer.write(false);
                writer.write(false);
                writer.write(false);
                writer.write(false);
            } else if(c== 'q'){ //0001
                writer.write(false);
                writer.write(false);
                writer.write(false);
                writer.write(true);

// write all bits once for "!"
            } else if(c == '!') { // 010 0001
                writer.write(false);
                writer.write(true);
                writer.write(false);

                writer.write(false);
                writer.write(false);
                writer.write(false);
                writer.write(true);
// write all bits once for "K"
            } else if(c == 'K') { // 100 1011
                writer.write(true);
                writer.write(false);
                writer.write(false);

                writer.write(true);
                writer.write(false);
                writer.write(true);
                writer.write(true);

            } else if( isSquareLetter ) { // if is a square letter which is A-H
                String cCapitalLetter = String.valueOf(c).toUpperCase(); // store it as capital letters

                if ( cCapitalLetter.equals("A")  ) { // A = 0001
                    writer.write(false);
                    writer.write(false);
                    writer.write(false);
                    writer.write(true);

                } else if ( cCapitalLetter.equals("B") ) { // B = 0010
                    writer.write(false);
                    writer.write(false);
                    writer.write(true);
                    writer.write(false);
                }  else if ( cCapitalLetter.equals("C") ) { // C = 0011
                    writer.write(false);
                    writer.write(false);
                    writer.write(true);
                    writer.write(true);
                }  else if ( cCapitalLetter.equals("D") ) { // D = 0100
                    writer.write(false);
                    writer.write(true);
                    writer.write(false);
                    writer.write(false);
                }  else if ( cCapitalLetter.equals("E") ) { // E = 0101
                    writer.write(false);
                    writer.write(true);
                    writer.write(false);
                    writer.write(true);
                }  else if ( cCapitalLetter.equals("F") ) { // F = 0110
                    writer.write(false);
                    writer.write(true);
                    writer.write(true);
                    writer.write(false);
                }   else if ( cCapitalLetter.equals("G") ) { // G = 0111
                    writer.write(false);
                    writer.write(true);
                    writer.write(true);
                    writer.write(true);
                }   else if ( cCapitalLetter.equals("H") ) { // H = 1000
                    writer.write(true);
                    writer.write(false);
                    writer.write(false);
                    writer.write(false);
                }
            }  else {

                throw new IllegalArgumentException("Unrecognized character: " + c);
            }
        }

        return writer.extract();
    }

    public static String decompress(byte[] data){

        BitReader reader = new BitReader(data);

        int n = reader.readInt();

        StringBuffer buffer = new StringBuffer(n);

        for(int i=0; i<n; i++){

            boolean a = reader.readBoolean();
            boolean b = reader.readBoolean();
            boolean c = reader.readBoolean();

            boolean d = reader.readBoolean();
            boolean e = reader.readBoolean();
            boolean f = reader.readBoolean();
            boolean g = reader.readBoolean();

            /**
             * For any number check the first check a, b, c if 011
             * any letter in those letter ('k', 'b') check a, b, c is if 110
             * any letter in those letter ('p', 'q', 'r') check a, b, c if 111
             *              * then check every letter or number with himself
             * check K
             * check !
             */

            if(!a && b && c){ // for any number starts with 011
                if ( !d && !e && !f && g ) { // for number 1 the binary code is 0001
                    buffer.append("1");
                } else if ( !d && !e && f && !g ) { // for number 2 the binary code is 0010
                    buffer.append("2");
                } else if ( !d && !e && f && g ) { // for number 3 the binary code is 0011
                    buffer.append("3");
                } else if ( !d && e && !f && !g ) { // for number 4 the binary code is 0100
                    buffer.append("4");
                } else if ( !d && e && !f && g ) { // for number 5 the binary code is 0101
                    buffer.append("5");
                } else if ( !d && e && f && !g ) { // for number 6 the binary code is 0110
                    buffer.append("6");
                } else if ( !d && e && f && g ) { // for number 7 the binary code is 0111
                    buffer.append("7");
                } else if ( d && !e && !f && !g ) { // for number 8 the binary code is 1000
                    buffer.append("8");
                } else if ( d && !e && !f && g ) { // for number 9 the binary code is 1001
                    buffer.append("9");
                }
            } else if(a && !b && !c) { // Square Letter starts with 100


                if( !d && !e && !f && g ) { // A=0001
                    buffer.append("a");
                } else if( !d && !e && f && !g ) {  // B=0010
                    buffer.append("b");
                } else if( !d && !e && f && g ) {  // C=0011
                    buffer.append("c");
                } else if( !d && e && !f && !g ) {  // D=0100
                    buffer.append("d");
                } else if( !d && e && !f && g ) {  // E=0101
                    buffer.append("e");
                } else if( !d && e && f && !g ) {  // F=0110
                    buffer.append("f");
                } else if( !d && e && f && g ) {  // G=0111
                    buffer.append("g");
                } else if( d && !e && !f && !g ) {  // H=1000
                    buffer.append("h");
                }

            } else if(a && b && !c) { // k OR b starts with 110

                if( d && !e && f && g ) { // k 1011
                    buffer.append("k");

                } else if( !d && !e && f && !g ) {  // b 0010
                    buffer.append("b");
                }
            }else if(a && b && c){ // for 'p' OR 'q' OR 'r') starts with 111
                if( !d && !e && !f && !g ) {  // p 0000
                    buffer.append("p");
                } else if( !d && !e && !f && g ) { // q 0001
                    buffer.append("q");
                } else if( !d && !e && f && !g ) { // r 0010
                    buffer.append("r");
                }
            }else if( a && !b && !c && d && !e && f && g ){ // for 'K'  100 1011
                buffer.append("K");
            }else if( !a && b && !c && !d && !e && !f && g ){ // for '!' 010 0001
                buffer.append("!");
            }
        }

        return buffer.toString();
    }
}
