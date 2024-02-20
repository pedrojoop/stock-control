document.getElementById('stock-control-link').addEventListener('click', function() {
    document.querySelector('.sidebar').classList.toggle('open');
});

function createBarChart(data, labels, title, containerId) {
    var chartContainer = document.getElementById(containerId);

    var myChart = new Chart(chartContainer, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: title,
                data: data,
                backgroundColor: 'rgba(255, 99, 132, 0.2)', // Cor de fundo das barras
                borderColor: 'rgba(255, 99, 132, 1)', // Cor da borda das barras
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function fetchAndCreateCharts() {
    fetch('/api/produtos/produtos-nome-quantidade')
        .then(response => response.json())
        .then(data => {
            const nomes = Object.keys(data);
            const quantidades = Object.values(data);
            createBarChart(quantidades, nomes, "Quantidade de Produtos", "quantidadeProdutosChart");
        })
        .catch(error => {
            console.error('Erro ao buscar dados de quantidade de produtos:', error);
        });

    fetch('/api/produtos/produtos-nome-valor')
        .then(response => response.json())
        .then(data => {
            const nomes = Object.keys(data);
            const valores = Object.values(data);
            createBarChart(valores, nomes, "Valor dos Produtos", "valorProdutosChart");
        })
        .catch(error => {
            console.error('Erro ao buscar dados de valor dos produtos:', error);
        });
}

fetchAndCreateCharts();
