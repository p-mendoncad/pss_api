package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Agendamento;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AgendamentoDTO {
    private Long id;
    private String data;
    private String horaEntrada;
    private String horaSaida;
    private String horario;
    private Servico servico;
    private Pet pet;

    public static AgendamentoDTO create(Agendamento agendamento){
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);

        return dto;
    }
}
