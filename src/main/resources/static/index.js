// Simulação de dados do estoque
var estoque = [
    { id: 1, nome: "Produto A", quantidade: 10, dataAdicao: "2024-02-15", dataValidade: "2025-02-15", valor: 10.50 },
    { id: 2, nome: "Produto B", quantidade: 20, dataAdicao: "2024-02-14", dataValidade: "2025-02-14", valor: 15.00 },
    { id: 3, nome: "Produto C", quantidade: 15, dataAdicao: "2024-02-13", dataValidade: "2025-02-13", valor: 20.75 }
];

// Função para exibir os dados do estoque na tabela
function exibirEstoque() {
    var tableBody = document.getElementById("tableBody");

    estoque.forEach(function(produto) {
        var row = document.createElement("tr");
        row.innerHTML = `
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.quantidade}</td>
            <td>${produto.dataAdicao}</td>
            <td>${produto.dataValidade}</td>
            <td>${produto.valor}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Função para exportar os dados da tabela para Excel
function exportarParaExcel() {
    alert("Exportando para Excel...");
}

// Event listener para o botão de exportar
document.querySelector(".export-btn").addEventListener("click", exportarParaExcel);

// Chamada da função para exibir os dados do estoque na tabela
exibirEstoque();
