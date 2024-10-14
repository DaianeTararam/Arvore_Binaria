package model.estrutura.arvore;

import model.estrutura.arvore.No;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinaria<T extends Comparable<T> >{

	private No<T> raiz;
	private int tamanho = 0;

	public ArvoreBinaria(){
		this.raiz = null;
	}

	public void adicionar(T valor){
		No<T> novo = new No<T> (valor);
		tamanho++;
		if (raiz == null){
			this.raiz = novo;
			return;
		}
		
		No<T> atual = this.raiz;
		while(true){
			if (novo.getValor().compareTo(atual.getValor()) < 0){
				if(atual.getMenor() != null){
					atual = atual.getMenor();
				}else{
					atual.setMenor(novo);
					break;
				}
			}else{
				if(atual.getMaior() != null){
					atual = atual.getMaior();
				}else{
					atual.setMaior(novo);
					break;
				}
			}
		}
	}

	public ListaEncadeadaSimples ordem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	public ListaEncadeadaSimples preOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	public ListaEncadeadaSimples posOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	private void ordem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			ordem(atual.getMenor(), lista);
			lista.append(atual.getValor());
			ordem(atual.getMaior(), lista);
		}
	}

	private void preOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			lista.append(atual.getValor());
			preOrdem(atual.getMenor(), lista);
			preOrdem(atual.getMaior(), lista);
		}
	}
	
	private void posOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			posOrdem(atual.getMenor(), lista);
			posOrdem(atual.getMaior(), lista);
			lista.append(atual.getValor());
			
		}
	}

	public boolean remover(T valor){
		No<T> atual = this.raiz;
		No<T> paiAtual = null;
		while(atual != null){
			if (atual.getValor().equals(valor)){
				break;
			}else if (valor.compareTo(atual.getValor()) < 0){
				paiAtual = atual;
				atual = atual.getMenor();
			}else{
				paiAtual = atual;
				atual = atual.getMaior();
			}
		}

		if(atual == null) return false;
		if(atual.getMaior() != null){
			No<T> substituto = atual.getMaior();
			No<T> paiSubstituto = atual;
			while(substituto.getMenor() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMenor();
			}
			substituto.setMenor(atual.getMenor());
			if(paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(substituto);
				}else{
					paiAtual.setMaior(substituto);
				}
			} else{
				this.raiz = substituto;
				paiSubstituto.setMenor(null);
				this.raiz.setMaior(paiSubstituto);
				this.raiz.setMenor(atual.getMenor());
			}
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == - 1){
				paiSubstituto.setMenor(null);
				substituto.setMaior(paiSubstituto);
			}else{
				paiSubstituto.setMaior(null);
			}
		}else if(atual.getMenor() != null){
			No<T> substituto = atual.getMenor();
			No<T> paiSubstituto = atual;
			while(substituto.getMaior() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMaior();
			}
			if(paiAtual != null){
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(substituto);
				}else{
					paiAtual.setMaior(substituto);
				}
			}else{
				this.raiz = substituto;
			}
	
			if(substituto.getValor().compareTo(paiSubstituto.getValor()) == -1){
				paiAtual.setMenor(null);
			}else{
				paiAtual.setMaior(null);
			}
		} else{
			if (paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(null);
				}else{
					paiAtual.setMaior(null);
				}
			}else{
				this.raiz = null;
			}
		}
		return true;
	}
}
			