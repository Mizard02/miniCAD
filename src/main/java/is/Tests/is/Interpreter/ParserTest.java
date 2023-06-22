package is.Interpreter;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.StringReader;
import java.util.ArrayList;


public class ParserTest extends TestCase {

    private Parser p;
    private StringReader sr;
    private String Comando = "";

    private void SyntaxParser(String s) throws SyntaxException{
        Comando = s;
        sr = new StringReader(Comando);
        p = new Parser(sr);
    }

    @Test
    public void testCreateCommand() {
        SyntaxParser("new circle (50.0) (100.0, 100.0)");
        assertSame(Create.class, p.getComando().getClass());
        SyntaxParser("new rectangle (100.0, 100.0) (200.0, 200.0)");
        assertSame(Create.class, p.getComando().getClass());
        SyntaxParser("new img (\"C:\\Users\\Giuseppe\\OneDrive\\UNI\\SECONDO ANNO\\2 SEM\\Ing Del Software\\mini CAD\\src\\is\\shapes\\model\\NyaNya.gif\") (300.0, 300.0)");
        assertSame(Create.class, p.getComando().getClass());
    }
    /*@Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCreatewithoutPos(){
        SyntaxParser("new circle (50.0)");
    }*/

    @Test
    public void testGroupCommand() {
        SyntaxParser("grp 2, 3");
        assertSame(Group.class, p.getComando().getClass());
        SyntaxParser("grp 1, 4"); //forse fallisce l'interpretazione
        assertSame(Group.class, p.getComando().getClass());
    }

    @Test
    public void testMoveCommand() {
        SyntaxParser("mv 1 (5.0, 3.0)");
        assertSame(MV.class, p.getComando().getClass());
        SyntaxParser("mvoff 2 (2.0, 1.0)");
        assertSame(MVOFF.class, p.getComando().getClass());
    }

    @Test
    public void testScaleCommand() {
        SyntaxParser("scale 3 2.0");
        assertSame(Scale.class, p.getComando().getClass());
    }

    @Test
    public void testListCommand() {
        SyntaxParser("ls 8");
        assertSame(ListID.class, p.getComando().getClass());
        SyntaxParser("ls circle");
        assertSame(ListType.class, p.getComando().getClass());
        SyntaxParser("ls all");
        assertSame(ListAll.class, p.getComando().getClass());
        SyntaxParser("ls groups");
        assertSame(ListaGroups.class, p.getComando().getClass());
    }

    @Test
    public void testAreaCommand() {
        SyntaxParser("area 2");
        assertSame(AreaID.class, p.getComando().getClass());
        SyntaxParser("AREA RECTANGLE");
        assertSame(AreaType.class, p.getComando().getClass());
        SyntaxParser("area all");
        assertSame(AreaAll.class, p.getComando().getClass());
    }

    @Test
    public void testPerimeterCommand() {
        SyntaxParser("perimeter 247");
        assertSame(PerimeterID.class, p.getComando().getClass());
        SyntaxParser("perimeter img");
        assertSame(PerimeterType.class, p.getComando().getClass());
        SyntaxParser("PERIMETER ALL");
        assertSame(PerimeterAll.class, p.getComando().getClass());
    }

    /*@Test(expected = SyntaxException.class)
    public void testIdOverMax(){
        SyntaxParser("perimeter 657678247");
    }*/

    @Test
    public void testUngroupCommand() {
        SyntaxParser("ungrp 4");
        assertSame(Ungroup.class, p.getComando().getClass());
    }

    @Test
    public void testRemoveCommand() {
        SyntaxParser("del  836");
        assertSame(Remove.class, p.getComando().getClass());
    }

    //provare a sciogliere un gruppo e poi eliminarlo
}