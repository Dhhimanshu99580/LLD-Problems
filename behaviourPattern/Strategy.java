package behaviourPattern;

import java.util.HashMap;
import java.util.Map;

/********* Strategy Pattern **********/

interface ReportStrategy {
    void generateReport(RequiredPojoForReportGeneration pojo);
}

class RequiredPojoForReportGeneration {
    // fields representing report data would go here
}

class PdfReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration pojo) {
        System.out.println("Generating PDF report...");
    }
}

class ExcelReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration pojo) {
        System.out.println("Generating Excel report...");
    }
}

class CsvReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration pojo) {
        System.out.println("Generating CSV report...");
    }
}

// Context class — holds the strategy and delegates to it
class ReportContext {
    private ReportStrategy reportStrategy;

    public ReportContext(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public void generateReport(RequiredPojoForReportGeneration pojo) {
        reportStrategy.generateReport(pojo);
    }
}

// Client — uses a map to pick strategy at runtime (factory-style)
class Client {
    private static final Map<String, ReportStrategy> reportStrategyMap = new HashMap<>();

    static {
        reportStrategyMap.put("pdf",   new PdfReportStrategy());
        reportStrategyMap.put("excel", new ExcelReportStrategy());
        reportStrategyMap.put("csv",   new CsvReportStrategy());
    }

    public static void main(String[] args) {
        String reportType = "pdf"; // comes from request
        ReportContext reportContext = new ReportContext(reportStrategyMap.get(reportType));
        reportContext.generateReport(new RequiredPojoForReportGeneration());
    }
}
