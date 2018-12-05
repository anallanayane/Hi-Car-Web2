package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findOne(Integer id) {
		return pessoaRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Pessoa save(Pessoa entity) {
		if(!PessoaService.isCPF(entity.getCpf()) || !PessoaService.isNome(entity.getNome()))
			return null;
		
		return pessoaRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Pessoa entity) {
		pessoaRepository.delete(entity);
	}
	
	public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
          
        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
	
	public static boolean isNome(String st) {
		if (st.length() < 5 || st.length() > 50 || st.indexOf(" ") == -1 || st.substring(0,st.indexOf(" ")).length() < 2 || st.substring(st.lastIndexOf(" ") + 1).length() < 2)
	        return false;
		
	    return true;
	}

}
	