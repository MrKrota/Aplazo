package com.caldelas.retoaplazo.controller;

import com.caldelas.retoaplazo.PagosRepository;
import com.caldelas.retoaplazo.entity.Pagos;
import com.caldelas.retoaplazo.entity.Term;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PagosController {

    @Autowired
    private PagosRepository pagosRepository;

    //CONSTANTS DEFINITION
    private final int MAX_TERMS = 52;
    private final int MIN_TERMS = 4;
    private final double MAX_AMOUNT = 999999.00;
    private final double MIN_AMOUNT = 1.00;
    private final double MIN_RATE = 1.00;
    private final double MAX_RATE = 100.00;

    @PostMapping(
            value = "/terms/request",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity create(@RequestBody String input) {
        Gson gson = new Gson();
        Term newTerm = gson.fromJson(input, Term.class);
        LocalDate currentDate = LocalDate.now();
        double amount = newTerm.getAmount();
        int terms = newTerm.getTerms();
        double rate = newTerm.getRate();

        if (!(MIN_AMOUNT < amount && amount < MAX_AMOUNT)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid amount. Please provide a valid amount");
        }
        if (!(MIN_TERMS <= terms && terms <= MAX_TERMS)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid terms. Please provide valid terms");
        }
        if (!(MIN_RATE < rate && rate < MAX_RATE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rate. Please provide a valid rate");
        }

        //(Interes simple + Cantidad) / semanas
        double weeklyPayment = ((amount * (rate / 100) * terms) + amount) / terms;

        String outFormat = "{\"payment_number\":%d,\"amount\": $%.2f,\"payment_date\":%s}";
        List<String> payments = new ArrayList<>();

        for (int i = 0; i < terms; i++) {
            payments.add(String.format(outFormat, i + 1, weeklyPayment, currentDate.plusWeeks(i)));
        }

        Pagos pagos = new Pagos();
        pagos.setInput(input);
        pagos.setOutput(payments.toString());
        pagosRepository.save(pagos);

        return ResponseEntity.ok(payments.toString());
    }

    @GetMapping("/list")
    public Iterable<Pagos> getPagos() {
        return pagosRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Pagos findPagosById(@PathVariable Integer id) {
        return pagosRepository.findPagosById(id);
    }

}
