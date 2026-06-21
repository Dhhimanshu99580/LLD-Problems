package behaviourPattern;

//
/********* Strategy Pattern **********/
public interface ReportStrategy {
    public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration);
}

// classes Implementing this
public class PdfReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
        // logic to generate pdf report
    }
}

public class ExcelReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
        // logic to generate excel report
    }
}

public class CsvReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
        // logic to generate csv report
    }
}

// Context class from where we will call the strategy
public class ReportContext{
    private ReportStrategy reportStrategy;
    public ReportContext() {
    }
    public ReportContext(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }
    public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
        reportStrategy.generateReport(requiredPojoForReportGeneration);
    }
}

// Client class to test the strategy pattern
public class Client {package behaviourPattern;

//
    /********* Strategy Pattern **********/
    public interface ReportStrategy {
        public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration);
    }

    // classes Implementing this
    public class PdfReportStrategy implements behaviourPattern.ReportStrategy {
        @Override
        public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
            // logic to generate pdf report
        }
    }

    public class ExcelReportStrategy implements behaviourPattern.ReportStrategy {
        @Override
        public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
            // logic to generate excel report
        }
    }

    public class CsvReportStrategy implements behaviourPattern.ReportStrategy {
        @Override
        public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
            // logic to generate csv report
        }
    }

    // Context class from where we will call the strategy
    public class ReportContext{
        private behaviourPattern.ReportStrategy reportStrategy;
        public ReportContext(behaviourPattern.ReportStrategy reportStrategy) {
            this.reportStrategy = reportStrategy;
        }
        public void generateReport(RequiredPojoForReportGeneration requiredPojoForReportGeneration) {
            reportStrategy.generateReport(requiredPojoForReportGeneration);
        }
    }

    // Client class to test the strategy pattern
    public class Client {
        // this is kind of factory method to get the strategy based on the request
        private static final Map<String,ReportStrategy> reportStrategyMap = new HashMap<>();
        static {
            reportStrategyMap.put("pdf", new PdfReportStrategy());
            reportStrategyMap.put("excel", new ExcelReportStrategy());
            reportStrategyMap.put("csv", new CsvReportStrategy());
        }
        public static void main(String[] args) {
            String reportType = "pdf"; // comes from request
            ReportContext reportContext = new ReportContext(reportStrategyMap.get(reportType));
            reportContext.generateReport(new RequiredPojoForReportGeneration());
        }
    }
}
