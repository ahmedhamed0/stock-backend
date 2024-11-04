package com.afs.stock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ErrorResponseModel", description = "Error Object")
public class ErrorResponseModel {

    @Schema(name = "statusCode", description = "returned status code")
    String statusCode;

    @Schema(name = "rejectionReason", description = "Description of the error")
    String rejectionReason;


    @Schema(name = "tracerId", description = "Tracer number")
    String tracerId;
}
