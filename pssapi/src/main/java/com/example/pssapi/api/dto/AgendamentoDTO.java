package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Agendamento;
import com.example.pssapi.model.entity.Funcionario;
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
    private Long idPet;
    private Long idFuncionario;
    private Long idServico;
    private String nomePet;
    private String nomeServico;
    private String nomeFuncionario;

    public static AgendamentoDTO create(Agendamento agendamento){
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.nomePet = agendamento.getPet().getNome();
        dto.nomeServico = agendamento.getServico().getNome();
        //dto.nomeFuncionario = agendamento.getFuncionario().getNome();

        return dto;
    }
}
