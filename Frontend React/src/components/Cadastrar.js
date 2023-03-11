import React from 'react';
import { useHistory, useParams } from 'react-router-dom';
import axios from 'axios';

import Button from './Button';

const Cadastrar = () => {
    const history = useHistory();
    
    const handleBackButtonClick = () => {
        history.goBack();
    }

    const enviarArquivo = (event) => {
        event.preventDefault();
        
        const url = "http://localhost:8080/arquivo";
        
        const arquivo = document.querySelector("#arquivo");
        const formData = new FormData();
       
        formData.append('image', arquivo.files[0]);
        formData.append('arquivoNome', arquivo.files[0].name);

        const config = {
            headers: {}
        };
        axios.post(url, formData, config).then((response) => {
            const imgId = response.data.id;
            axios.get("http://localhost:8080/produtos", config).then((response) => {
                const prodId = response.data[response.data.length-1].id
                cadastrarProduto(imgId, prodId)
            })


        })

    }

    const cadastrarProduto = (idImg, prodId) => {
        console.log(idImg)
        console.log(prodId)
        const nome = document.querySelector("#nome");
        const preco = document.querySelector("#preco");
        const quantidadeEstoque = document.querySelector("#quantidade");
        const categoria = document.querySelector("#categoria");

        const url = "http://localhost:8080/produtos";

        const dados = {
            "nome": nome.value,
            "preco": preco.value,
            "quantidadeEstoque": quantidadeEstoque.value,
        }
        const config = {
            headers: {},
        };
        axios.post(url, dados, config).then((response) => {
            axios.put(`http://localhost:8080/produtos/${prodId+1}/addArquivo/${idImg}`, config).then((response) => {
                console.log(response.data)
                history.goBack()
            })
        })
    }

    return (
        <>
            <h2>Formulário de cadastro!</h2>
            <form method="POST" action="http://localhost:8080/produtos">
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome"></input>
                </div>
                <div>
                    <label for="preco">Preço</label>
                    <input type="number" name="preco" id="preco"></input>
                </div>
                <div>
                    <label for="quantidade">Quantidade</label>
                    <input type="number" name="quantidade" id="quantidade"></input>
                </div>
                <div>
                    <label for="categoria">Selecione uma categoria</label>
                    <select name="categoria" id="categoria">
                        <option value="Mobilidade">Mobilidade</option>
                        <option value="Descanso">Descanso</option>
                    </select>
                </div>
                <div>
                    <label for="arquivo">Selecione uma imagem</label>
                    <input type="file" name="arquivo" id="arquivo"></input>
                </div>
                <button type="submit" onClick={enviarArquivo}>Cadastrar</button>
            </form>
            <Button onClick={handleBackButtonClick}>Voltar</Button>
        </>
    )
}
export default Cadastrar;