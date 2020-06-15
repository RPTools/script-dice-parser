package net.rptools.mtscript.symboltable.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardSymbolTableStackTest {

  private SymbolTableFactory symbolTableFactory;
  private SymbolTable[] symbolTables;



  @BeforeEach
  public void setup() {
    symbolTableFactory = mock(SymbolTableFactory.class);

    symbolTables = new SymbolTable[] {
        mock(SymbolTable.class),
        mock(SymbolTable.class),
        mock(SymbolTable.class),
        mock(SymbolTable.class),
        mock(SymbolTable.class)
    };

    for (int i = 0; i < symbolTables.length; i++) {
      when(symbolTableFactory.create(i)).thenReturn(symbolTables[i]);
      when(symbolTables[i].getLevel()).thenReturn(i);
    }
  }


  @Test
  public void nestingLevel() {

    var stableStack = new StandardSymbolTableStack(symbolTableFactory);

    assertEquals(0, stableStack.getNestingLevel());
    assertEquals(symbolTables[0], stableStack.getLocalSymbolTable());

    stableStack.push();
    assertEquals(1, stableStack.getNestingLevel());
    assertEquals(symbolTables[1], stableStack.getLocalSymbolTable());

    stableStack.push();
    assertEquals(2, stableStack.getNestingLevel());
    assertEquals(symbolTables[2], stableStack.getLocalSymbolTable());

    stableStack.push();
    assertEquals(3, stableStack.getNestingLevel());
    assertEquals(symbolTables[3], stableStack.getLocalSymbolTable());

    stableStack.push();
    assertEquals(4, stableStack.getNestingLevel());
    assertEquals(symbolTables[4], stableStack.getLocalSymbolTable());

    for (int i = 0; i < symbolTables.length; i++) {
      assertEquals(symbolTables[i], stableStack.getSymbolTable(i));
    }

    stableStack.pop();
    assertEquals(3, stableStack.getNestingLevel());
    assertEquals(symbolTables[3], stableStack.getLocalSymbolTable());

    stableStack.pop();
    assertEquals(2, stableStack.getNestingLevel());
    assertEquals(symbolTables[2], stableStack.getLocalSymbolTable());

    stableStack.pop();
    assertEquals(1, stableStack.getNestingLevel());
    assertEquals(symbolTables[1], stableStack.getLocalSymbolTable());

    stableStack.pop();
    assertEquals(0, stableStack.getNestingLevel());
    assertEquals(symbolTables[0], stableStack.getLocalSymbolTable());

    assertThrows(IllegalStateException.class, stableStack::pop);

    assertThrows(IllegalStateException.class, () -> stableStack.getSymbolTable(10));

  }


  @Test
  public void create() {
    var stableStack = new StandardSymbolTableStack(symbolTableFactory);
    verify(symbolTables[0], times(0)).create(any(String.class));
    stableStack.create("test");
    verify(symbolTables[0], times(1)).create("test");
    verify(symbolTables[0], times(1)).create(any(String.class));

    stableStack.create("test 123");
    verify(symbolTables[0], times(1)).create("test 123");
    verify(symbolTables[0], times(2)).create(any(String.class));

    stableStack.push();
    stableStack.create("test testing");
    verify(symbolTables[0], times(0)).create("test testing");
    verify(symbolTables[0], times(2)).create(any(String.class));

    verify(symbolTables[1], times(1)).create("test testing");
    verify(symbolTables[1], times(1)).create(any(String.class));

    stableStack.pop();
    stableStack.create("test testing");
    verify(symbolTables[1], times(1)).create("test testing");
    verify(symbolTables[1], times(1)).create(any(String.class));

    verify(symbolTables[0], times(1)).create("test testing");
    verify(symbolTables[0], times(3)).create(any(String.class));

  }


  @Test
  public void lookupLocal() {
    SymbolTableEntry level0Test1 = mock(SymbolTableEntry.class);
    SymbolTableEntry level0Test2 = mock(SymbolTableEntry.class);
    SymbolTableEntry level1Test1 = mock(SymbolTableEntry.class);
    when(symbolTables[0].lookup("test1")).thenReturn(Optional.of(level0Test1));
    when(symbolTables[0].lookup("test2")).thenReturn(Optional.of(level0Test2));
    when(symbolTables[1].lookup("test1")).thenReturn(Optional.of(level1Test1));

    var stableStack = new StandardSymbolTableStack(symbolTableFactory);
    assertTrue(stableStack.lookupLocal("test1").isPresent());
    assertEquals(level0Test1, stableStack.lookupLocal("test1").get());
    assertTrue(stableStack.lookupLocal("not test1").isEmpty());

    stableStack.push();
    assertTrue(stableStack.lookupLocal("test1").isPresent());
    assertEquals(level1Test1, stableStack.lookupLocal("test1").get());
    assertTrue(stableStack.lookupLocal("not test1").isEmpty());

    assertTrue(stableStack.lookupLocal("test2").isEmpty());
    assertTrue(stableStack.lookupLocal("not test2").isEmpty());

    stableStack.pop();
    assertTrue(stableStack.lookupLocal("test2").isPresent());
    assertEquals(level0Test2, stableStack.lookupLocal("test2").get());
    assertTrue(stableStack.lookupLocal("not test2").isEmpty());
  }

  @Test
  public void lookup() {
    SymbolTableEntry level0Test1 = mock(SymbolTableEntry.class);
    SymbolTableEntry level0Test2 = mock(SymbolTableEntry.class);
    SymbolTableEntry level1Test1 = mock(SymbolTableEntry.class);
    when(symbolTables[0].lookup("test1")).thenReturn(Optional.of(level0Test1));
    when(symbolTables[0].lookup("test2")).thenReturn(Optional.of(level0Test2));
    when(symbolTables[1].lookup("test1")).thenReturn(Optional.of(level1Test1));

    var stableStack = new StandardSymbolTableStack(symbolTableFactory);
    assertTrue(stableStack.lookup("test1").isPresent());
    assertEquals(level0Test1, stableStack.lookup("test1").get());
    assertTrue(stableStack.lookup("not test1").isEmpty());

    stableStack.push();
    assertTrue(stableStack.lookup("test1").isPresent());
    assertEquals(level1Test1, stableStack.lookup("test1").get());
    assertTrue(stableStack.lookup("not test1").isEmpty());

    assertTrue(stableStack.lookup("test2").isPresent());
    assertEquals(level0Test2, stableStack.lookup("test2").get());
    assertTrue(stableStack.lookup("not test2").isEmpty());
  }


}