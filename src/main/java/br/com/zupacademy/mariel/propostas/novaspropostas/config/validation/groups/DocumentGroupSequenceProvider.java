package br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.groups;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.zupacademy.mariel.propostas.novaspropostas.PropostaRequest;

public class DocumentGroupSequenceProvider implements DefaultGroupSequenceProvider<PropostaRequest> {

	public List<Class<?>> getValidationGroups(PropostaRequest dto) {
	    List<Class<?>> groups = new ArrayList<>();
	    
	    groups.add(PropostaRequest.class);
	    
	    if (dto != null) {
	      if ("PF".equalsIgnoreCase(dto.getTipo())) {
	        groups.add(PessoaFisica.class);
	      } else if ("PJ".equalsIgnoreCase(dto.getTipo())) {
	        groups.add(PessoaJuridica.class);
	      }
	    }
	    
	    return groups;
	  }

}
