package org.basex.test.fuse;

import static org.basex.data.DataText.*;
import static org.junit.Assert.*;
import static org.basex.util.Token.*;

import java.io.File;

import org.basex.core.proc.DropDB;
import org.deepfs.fs.DeepFS;
import org.junit.After;
import org.junit.Before;

/**
 * Tests modeling a file hierarchy in XML.
 *
 * [AH] Test needs to be reworked.
 * @author Workgroup DBIS, University of Konstanz 2005-09, ISC License
 * @author Alexander Holupirek
 */
public final class DeepBaseTest {
  /** Name of test database. */
  private static final String DBNAME = DeepBaseTest.class.getSimpleName();
  /** File name for test output. */
  private static final String TESTFILE = "/tmp/deepbasetest.xml";
  /** DeepBase reference to test. */
  private DeepFS dbfs;

  /**
   * Creates the database.
   */
  @Before
  public void setUp() {
    dbfs = new DeepFS(DBNAME, string(NOTMOUNTED));
  }

  /**
   * Cleans up.
   */
  @After
  public void tearDown() {
    dbfs.umount();
    DropDB.drop(DBNAME, dbfs.getContext().prop);
    new File(TESTFILE).delete();
  }

  /**
   * Makes some directories and test return code and composed XML.
   * [AH] update test case
   */
  //@Test
//  public void testMkdir() {
//    // mkdir should only accept directories (wrong mode test).
//    assertEquals("mkdir -1", -1, dbfs.mkdir("/a/x", 0100644));
//    assertEquals("mkdir 0", 4, dbfs.mkdir("/a", 0040755));
//    assertEquals("mkdir 0", 6, dbfs.mkdir("/a/b", 0040755));
//    // non-existing parent directories.
//    assertEquals("mkdir -1", -1, dbfs.mkdir("/a/b/c/d/e", 0040755));
//    assertEquals("mkdir 0", 8, dbfs.mkdir("/a/b/c", 0040755));
//    assertEquals("mkdir 0", 10, dbfs.mkdir("/a/b/d", 0040755));
//    assertEquals("mkdir 0", 12, dbfs.mkdir("/a/c", 0040755));
//    final String r3 = "<deepfuse mountpoint=\"unknown\">"
//        + "<dir name=\"a\"><dir name=\"b\"><dir name=\"c\"/>"
//        + "<dir name=\"d\"/></dir><dir name=\"c\"/></dir></deepfuse>";
//  //  assertEquals("mkdir r3", r3, query("/").trim());
//    // already exists (is getattr's task)
//  }

  /**
   * Creates regular file and check id returned.
   * [AH] update test case
   */
  //@Test
  public void testCreate() {
    assertEquals("mkdir 0", 4, dbfs.mkdir("/a", 0040755));
    // create returns id
    assertEquals("create", 5, dbfs.create("/a/file.txt", 0100644));
    // wrong mode
    assertEquals("create", -1, dbfs.create("/a/dir", 0040755));
    // no parent directory to insert
    assertEquals("create", -1, dbfs.create("/a/b/c/file.txt", 0100644));
    assertEquals("mkdir 0", 7, dbfs.mkdir("/a/b", 0040755));
   // query("/");
    assertEquals("create", 9, dbfs.create("/a/b/file.txt", 0100644));
  }

//  /**
//   * Getattr resolves pathnames and returns associated id or -1.
//   * [AH] update test case
//   */
//  //@Test
//  public void testGetAttr() {
//    //loadTestDB();
//    assertEquals("getattr", 3, dbfs.getattr("/afile"));
//    assertEquals("getattr", 11, dbfs.getattr("/a/b/cfile"));
//    assertEquals("getattr", -1, dbfs.getattr("/a/b/x/cfile"));
//  }

  /*
   * Removes a file (rmdir and unlink are handled the same way).
  @Test
  public void testUnlink() {
    loadTestDB();
    assertEquals("unlink", 0, dbfs.unlink("/afile"));
    query("/");
    assertEquals("unlink", 0, dbfs.unlink("/"));
    query("/");
  }
   */

  /*
   * Reads directory entries (rmdir and unlink are handled the same way).
  @Test
  public void testReaddir() {
    loadTestDB(ctx);
    assertEquals("unlink", 0, dbfs.unlink("/afile"));
    query("/");
    assertEquals("unlink", 0, dbfs.unlink("/"));
    query("/");
  }
   */

//  /**
//   * Loads a pre-filled DeepFS XML instance.
//   */
//  private void loadTestDB() {
//    try {
//      final String path = "test/org/basex/test/fuse/getattrtest.xml";
//      final XMLParser parser = new XMLParser(IO.get(path), ctx.prop);
//      final Data data = CreateDB.xml(ctx, parser, DBNAME);
//      ctx.openDB(data);
//      dbfs.data = data;
//    } catch(final IOException ex) {
//      ex.printStackTrace();
//      fail("Problem loading test database.");
//    }
//  }

//  /**
//   * Evaluates XQuery and return result as string.
//   * @param query to execute
//   * @return result as string
//   */
//  private String query(final String query) {
//    try {
//      final Nodes n = new QueryProcessor(query, ctx).queryNodes();
//      final PrintOutput out = new PrintOutput(TESTFILE);
//      n.serialize(new XMLSerializer(out));
//      out.close();
//
//      final FileInputStream f = new FileInputStream(TESTFILE);
//      final StringBuilder sb = new StringBuilder();
//      final byte[] b = new byte[IO.BLOCKSIZE];
//      while(f.read(b) != -1) sb.append(new String(b));
//      System.err.println(sb.toString());
//      return sb.toString();
//    } catch(final Exception ex) {
//      ex.printStackTrace();
//      return "";
//    }
//  }
}
