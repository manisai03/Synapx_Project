package com.example.demo.ControllerLayer;

import com.example.demo.DTO.ClaimResponse;
import com.example.demo.ServiceLayer.ClaimProcessingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClaimControllerTest {

    @Test
    void shouldProcessFile() throws Exception {

        ClaimProcessingService service = Mockito.mock(ClaimProcessingService.class);

        Mockito.when(service.process(Mockito.any()))
                .thenReturn(new ClaimResponse(
                        "file.pdf","CLM-1",100,90,
                        null, List.of(),"Fast Track","Test"));

        ClaimController controller = new ClaimController(service);

        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller).build();

        MockMultipartFile file =
                new MockMultipartFile("file","file.pdf",
                        MediaType.APPLICATION_PDF_VALUE,"data".getBytes());

        mockMvc.perform(multipart("/api/claims/process")
                        .file(file))
                .andExpect(status().isOk());
    }
}
