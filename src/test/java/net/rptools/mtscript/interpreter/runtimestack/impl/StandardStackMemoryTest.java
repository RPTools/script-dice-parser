package net.rptools.mtscript.interpreter.runtimestack.impl;

import static com.ibm.icu.impl.locale.KeyTypeData.ValueType.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocation;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocationFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardStackMemoryTest {

  private SymbolTable symbolTable;
  private StackMemoryLocationFactory stackMemoryLocationFactory;
  private StandardStackMemory standardStackMemory;
  private Set<SymbolTableEntry> symbolTableEntries = new HashSet<>();



  @BeforeEach
  public void setup() {
    symbolTable = mock(SymbolTable.class);
    stackMemoryLocationFactory = mock(StackMemoryLocationFactory.class);
    for (var mtd : MTSTypeDefinition.values()) {
      SymbolTableEntry entry = mock(SymbolTableEntry.class);
      when(entry.getName()).thenReturn("test " + mtd.name());
      when(entry.getTypeDefinition()).thenReturn(mtd);
      symbolTableEntries.add(entry);
    }

    when(symbolTable.getEntries()).thenReturn(symbolTableEntries);
    when(stackMemoryLocationFactory.create(any(MTSType.class))).thenReturn(mock(StackMemoryLocation.class));
    when(stackMemoryLocationFactory.create(null)).thenReturn(mock(StackMemoryLocation.class));
    standardStackMemory = new StandardStackMemory(symbolTable, stackMemoryLocationFactory);

  }

  @Test
  public void create() {

    for (var entry : symbolTableEntries) {
      var mtd = entry.getTypeDefinition();
      switch (mtd)  {
        case CONSTANT, VARIABLE, PARAMETER ->
          verify(entry, times(1)).getType();
        default ->
          verify(entry, times(0)).getType();
      }
    }
  }


  @Test
  public void getMemoryLocation() {
    for (var entry : symbolTable.getEntries())  {
      var mtd = entry.getTypeDefinition();
      switch (mtd)  {
        case CONSTANT, VARIABLE, PARAMETER ->
            assertTrue(standardStackMemory.getMemoryLocation(entry.getName()).isPresent());
        default ->
            assertTrue(standardStackMemory.getMemoryLocation(entry.getName()).isEmpty());
      }
    }
  }

  @Test
  public void getMemoryLocationNames() {
    assertEquals(3, standardStackMemory.getMemoryLocationNames().size());
    for (var entry : symbolTable.getEntries()) {
      var mtd = entry.getTypeDefinition();
      switch (mtd)  {
        case CONSTANT, VARIABLE, PARAMETER ->
            assertTrue(standardStackMemory.getMemoryLocationNames().contains(entry.getName()));
        default ->
            assertFalse(standardStackMemory.getMemoryLocationNames().contains(entry.getName()));
      }    }
  }
  
}