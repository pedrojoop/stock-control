
document.querySelector('.Logo').addEventListener('click', function() {
    document.querySelector('.sidebar').classList.toggle('open');
});

window.addEventListener('click', function(event) {
    var sidebar = document.querySelector('.sidebar');
    if (!event.target.matches('.Logo') && sidebar.classList.contains('open')) {
        sidebar.classList.remove('open');
    }
});

document.querySelector('.export-btn').addEventListener('click', exportToExcel);
function exportToExcel() {
    var table = document.querySelector(".table");
    var wb = XLSX.utils.table_to_book(table, {sheet: "Estoque"});
    XLSX.writeFile(wb, 'estoque.xlsx');
}

document.getElementById('searchButton').addEventListener('click', function() {
    var input = document.getElementById('searchInput').value.toUpperCase();
    var table = document.getElementById('tableBody');
    var rows = table.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var id = row.cells[0].textContent.toUpperCase();
        var nome = row.cells[1].textContent.toUpperCase();

        if (id.includes(input) || nome.includes(input)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    }
});

document.addEventListener('DOMContentLoaded', function() {
    function mostrarTodosProdutos() {
        fetch('/api/produtos')
            .then(response => response.json())
            .then(produtos => {
                const tableBody = document.getElementById('tableBody');
                tableBody.innerHTML = '';
                produtos.forEach(produto => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${produto.id}</td>
                        <td>${produto.nome}</td>
                        <td>${produto.quantidade}</td>
                        <td>${produto.dataDeAdicao}</td>
                        <td>${produto.dataDeValidade}</td>
                        <td>${produto.valor}</td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Erro ao buscar produtos:', error);
                alert('Erro ao buscar produtos. Por favor, tente novamente mais tarde.');
            });
    }

    mostrarTodosProdutos();
});
