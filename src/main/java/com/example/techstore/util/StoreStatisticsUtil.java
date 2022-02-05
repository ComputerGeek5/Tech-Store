package com.example.techstore.util;

import com.example.techstore.model.Bill;
import com.example.techstore.model.Employee;
import com.example.techstore.model.Supplier;
import com.example.techstore.model.abst.User;
import com.example.techstore.repository.BillRepository;
import com.example.techstore.repository.SupplierRepository;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.repository.impl.BillRepositoryImpl;
import com.example.techstore.repository.impl.SupplierRepositoryImpl;
import com.example.techstore.repository.impl.UsersRepositoryImpl;
import com.example.techstore.statistics.StoreStatistic;
import com.example.techstore.util.enumerator.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StoreStatisticsUtil {
    private static final BillRepository billRepository;
    private static final SupplierRepository supplierRepository;
    private static final UserRepository userRepository;

    static {
        billRepository = new BillRepositoryImpl();
        supplierRepository = new SupplierRepositoryImpl();
        userRepository = new UsersRepositoryImpl();
    }

    public static List<StoreStatistic> search(LocalDate fromDate, LocalDate toDate) {
        List<StoreStatistic> storeStatistics = new ArrayList<>();

        StoreStatistic soldStatistic = calculateSoldAmount(fromDate, toDate);
        StoreStatistic boughtStatistic = calculateBoughtAmount(fromDate, toDate);
        StoreStatistic salaryStatistic = calculateSalaries(fromDate, toDate);
        storeStatistics.addAll(Arrays.asList(soldStatistic, boughtStatistic, salaryStatistic));

        StoreStatistic profitStatistic = calculateProfit(storeStatistics);
        storeStatistics.add(profitStatistic);

        return storeStatistics;
    }

    private static StoreStatistic calculateSoldAmount(LocalDate fromDate, LocalDate toDate) {
        String source = "Sold";
        StoreStatistic.Type type = StoreStatistic.Type.DEBIT;
        double amount = 0;

        List<Bill> bills = billRepository.findAll()
                .stream()
                .filter(bill -> {
                    if (fromDate == null || toDate == null) {
                        return true;
                    }

                    if ((bill.getIssueDate().equals(fromDate) || bill.getIssueDate().isAfter(fromDate)) && (bill.getIssueDate().equals(toDate) || bill.getIssueDate().isBefore(toDate))) {
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toList());

        for (Bill bill: bills) {
            amount += bill.getTotal();
        }

        StoreStatistic soldStatistic = new StoreStatistic(source, amount, type);
        return soldStatistic;
    }

    private static StoreStatistic calculateBoughtAmount(LocalDate fromDate, LocalDate toDate) {
        String source = "Bought";
        StoreStatistic.Type type = StoreStatistic.Type.CREDIT;
        double amount = 0;

        List<Supplier> suppliers = supplierRepository.findAll()
                .stream()
                .filter(supplier -> {
                    if (fromDate == null || toDate == null) {
                        return true;
                    }

                    if ((supplier.getRegisterDate().equals(fromDate) || supplier.getRegisterDate().isAfter(fromDate)) && (supplier.getRegisterDate().equals(toDate) || supplier.getRegisterDate().isBefore(toDate))) {
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toList());

        for (Supplier supplier: suppliers) {
            int quantity = supplier.getCdQuantity();
            double buyPrice = supplier.getCd().getBuyPrice();

            amount += quantity * buyPrice;
        }

        StoreStatistic boughtStatistic = new StoreStatistic(source, amount, type);
        return boughtStatistic;
    }

    private static StoreStatistic calculateSalaries(LocalDate fromDate, LocalDate toDate) {
        String source = "Salaries";
        StoreStatistic.Type type = StoreStatistic.Type.CREDIT;
        double amount = 0;

        List<Employee> employees = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.MANAGER || user.getRole() == Role.CASHIER)
                .map(user -> (Employee) user)
                .collect(Collectors.toList());

        for (Employee employee: employees) {
            amount += employee.getSalary();
        }

        StoreStatistic salaryStatistic = new StoreStatistic(source, amount, type);
        return salaryStatistic;
    }

    private static StoreStatistic calculateProfit(List<StoreStatistic> storeStatistics) {
        String source = "Sold";
        StoreStatistic.Type type = null;
        double amount = 0;

        for (StoreStatistic storeStatistic: storeStatistics) {
            if (storeStatistic.getType() == StoreStatistic.Type.CREDIT) {
                amount -= storeStatistic.getAmount();
            } else if (storeStatistic.getType() == StoreStatistic.Type.DEBIT) {
                amount += storeStatistic.getAmount();
            }
        }

        StoreStatistic profitStatistic = new StoreStatistic(source, amount, type);
        return profitStatistic;
    }
}
