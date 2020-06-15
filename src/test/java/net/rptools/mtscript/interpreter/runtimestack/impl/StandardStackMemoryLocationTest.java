package net.rptools.mtscript.interpreter.runtimestack.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.rptools.mtscript.types.MTSType;
import org.junit.jupiter.api.Test;

class StandardStackMemoryLocationTest {


  @Test
  public void getType() {
    MTSType mtsType = mock(MTSType.class);

    var memLoc = new StandardStackMemoryLocation(mtsType);
    assertEquals(mtsType, memLoc.getType());
  }


  @Test
  public void value() {
    MTSType mtsType = mock(MTSType.class);

    var memLoc = new StandardStackMemoryLocation(mtsType);

    Random random = new Random();

    for (int i = 0; i < 100; i++) {
      int num = random.nextInt(100) + 1;
      memLoc.setValue(num);
      assertEquals(num, memLoc.getValue());

      String val = " ".repeat(num);
      memLoc.setValue(val);
      assertEquals(val, memLoc.getValue());
    }

    var list = new ArrayList<Object>();
    memLoc.setValue(list);
    assertEquals(list, memLoc.getValue());


    var map = new HashMap<Object, Object>();
    memLoc.setValue(map);
    assertEquals(map, memLoc.getValue());
  }

}