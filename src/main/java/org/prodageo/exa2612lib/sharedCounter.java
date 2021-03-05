package org.prodageo.exa2612lib;

import java.io.File;
// import java.nio.File;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;
// import java.nio.charset.Charset;

public class sharedCounter 
{
    public static int theSharedCounter ;

    // a static variable : the same for all objects instances of the sharedCounter class
    private static int thePrivateStaticSharedCounter ;

    public static int getPrivateStaticSharedCounter ()
    {
        return thePrivateStaticSharedCounter ;
    }

    // a non-static variable : specific to instance object in which they are created. 
    private int thePrivateSharedCounter ;

    public int getPrivateSharedCounter ()
    {
        return thePrivateSharedCounter ;
    }

    public int incrementPrivateSharedCounter ()
    {
        thePrivateSharedCounter = thePrivateSharedCounter + 1 ;
        return thePrivateSharedCounter ;
    }


    // constructor : set the shared memory
    private CharBuffer theSharedMemoryBuffer ;

	public int incrementSharedMemoryCounter ()
    {
        thePrivateSharedCounter = thePrivateSharedCounter + 1 ;
		
		/*  https://stackoverflow.com/questions/46343616/how-can-i-convert-a-char-to-int-in-java/46343671
		
			char x = '9';
			int y = Character.getNumericValue(x);   //use a existing function
			System.out.println(y + " " + (y + 1));  // 9  10
		*/
		char c ;
		c = theSharedMemoryBuffer.get () ;
		System.out.print( c );
		int i = c - '0' ;
		i = i + 1 ;
		c = (char) i ;
		
		String s = String.valueOf(c);
		char[] bufferChunk = s.toCharArray();
        theSharedMemoryBuffer.put( bufferChunk );
		
        return i ;
    }





    public sharedCounter ( ) throws Throwable {
        // https://webdevdesigner.com/q/shared-memory-between-two-jvms-21766/

        // https://github.com/caplogic/Mappedbus/tree/master
        // In the code above the file "/tmp/test" is on disk and thus it's memory mapped by the library. To use the library with shared memory instead, point to a file in "/dev/shm", for example, "/dev/shm/test".
        File f = new File( "/dev/shm/test" );

        FileChannel channel = FileChannel.open( f.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE );

        MappedByteBuffer b = channel.map( MapMode.READ_WRITE, 0, 4096 );
        theSharedMemoryBuffer = b.asCharBuffer();

        char[] string = "0".toCharArray();
        theSharedMemoryBuffer.put( string );

        /*

            // read shared memory
            char c;
            while( ( c = charBuf.get() ) != 0 ) {
                System.out.print( c );
            }
            System.out.println();
        */
    }

}
