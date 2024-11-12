package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.databases.functions;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.annotations.LibraryClass;
import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;

@LibraryClass
public class MySQLDBFunction implements FunctionContributor {
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry().registerNamed(
                "ST_Distance_Sphere", functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.DOUBLE)
        );
        // Define More Vendor Specific DB Function if needed
    }
}
