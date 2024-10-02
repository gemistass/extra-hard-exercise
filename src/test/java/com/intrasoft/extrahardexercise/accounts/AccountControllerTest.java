package com.intrasoft.extrahardexercise.accounts;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountRepository).build();
    }

    @Test
    void shouldGetAccountsByBeneficiaryId() throws Exception {

        ArrayList<Account> account = new ArrayList<Account>();
        account.add(new Account(1, 2));

        when(accountRepository.findByBeneficiaryId(1)).thenReturn(account);
        mockMvc.perform(get("/api/v1/accounts"));

    }
}
