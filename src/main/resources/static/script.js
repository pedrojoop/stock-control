
// Função para abrir ou fechar a sidebar ao clicar em "Stock-Control"
document.querySelector('.Logo').addEventListener('click', function() {
    document.querySelector('.sidebar').classList.toggle('open');
});

// Função para fechar a sidebar ao clicar fora dela
window.addEventListener('click', function(event) {
    var sidebar = document.querySelector('.sidebar');
    if (!event.target.matches('.Logo') && sidebar.classList.contains('open')) {
        sidebar.classList.remove('open');
    }
});

document.querySelector('.export-btn').addEventListener('click', exportToExcel);
function exportToExcel() {
    /* Seleciona a tabela */
    var table = document.querySelector(".table");
    /* Cria uma nova planilha */
    var wb = XLSX.utils.table_to_book(table, {sheet: "Estoque"});
    /* Salva o arquivo */
    XLSX.writeFile(wb, 'estoque.xlsx');
}

