package ua.vstup.service;

import ua.vstup.domain.Statement;

public interface StatementService {
    void createStatement();

    Statement getUnfinalizedStatement();

    void finalizeStatement();
}
