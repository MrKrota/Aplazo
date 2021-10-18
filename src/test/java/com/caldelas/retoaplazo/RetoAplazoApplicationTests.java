package com.caldelas.retoaplazo;

import com.caldelas.retoaplazo.controller.PagosController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class RetoAplazoApplicationTests {

    @Autowired
    private PagosController controller;

    @Test
    public void contextLoads() throws Exception {
        Assert.notNull(controller);
    }

}
