import React from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

import Button from './Button';

const Produto = ( { produto, handleProdutoDeletion } ) => {
    const imagem = `http://localhost:8080/arquivo/${produto.idArquivo}`;
    const url = `http://localhost:8080/produtos/${produto.id}`
    const excluir = () => {
        axios.delete(url)
        .then((response) => {
            handleProdutoDeletion(produto.id)
            console.log("Produto excluído")
            console.log(response)
          });      
    }
    
    const history = useHistory();
    const handleProdutoEditarClick = () => {
        history.push(`/${produto.id}/${produto.nome}/${produto.preco}/${produto.quantidadeEstoque}`)
    }
    
    return (
        <div>
            <img alt="Imagem produto" src={imagem} width="200px"></img>
            <p>Identificação: {produto.id}</p>
            <p>Nome: {produto.nome}</p>
            <p>Preço: {produto.preco}</p>
            <p>Disponíveis: {produto.quantidadeEstoque}</p>
            <Button onClick={handleProdutoEditarClick}>Editar</Button>
            <Button onClick={excluir}>Excluir</Button>
        </div>
    );
}



export default Produto;
