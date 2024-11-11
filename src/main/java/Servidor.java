import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

// Classe servidor para criar um servidor TCP
public class Servidor {

    public static void main(String[] args) {
        // Declaração de um mapa para armazenar perguntas e respostas
        Map<String, String> respostas = new HashMap<>();
        respostas.put("TUDO BEM?", "Sim, tudo ótimo!");
        respostas.put("QUAIS FILMES ESTAO EM CARTAZ?", "Hoje, está em cartaz o filme da Patrulha Canina.");
        respostas.put("QUANTO CUSTA O AEROLIN?", "O Aerolin custa R$29,00.");
        respostas.put("SAIR", "Obrigado por utilizar nosso sistema!");
        respostas.put(("OLA"), "Oi! Como posso ajudar você hoje?");
        respostas.put("OI", "Olá! Em que posso ajudar?");
        respostas.put("COMO VOCE ESTA?", "Estou aqui, sempre pronto para ajudar!");
        respostas.put("OBRIGADO", "De nada! Se precisar de mais alguma coisa, estarei por aqui.");
        respostas.put("QUAL A PREVISAO DO TEMPO?", "Infelizmente, não posso consultar a previsão agora, mas você pode checar no seu aplicativo de clima.");
        respostas.put("VAI CHOVER HOJE?", "Sugiro conferir a previsão do tempo para ter certeza.");
        respostas.put("QUAL A TEMPERATURA AGORA?", "Você pode verificar no seu aplicativo de clima preferido.");
        respostas.put("COMO ESTA O TRANSITO?", "Não tenho acesso a informações de trânsito, mas aplicativos de mapas podem ajudar!");
        respostas.put("QUAL O MELHOR CAMINHO PARA O TRABALHO?", "Você pode conferir o melhor trajeto no seu aplicativo de navegação.");
        respostas.put("QUAL O HORARIO DO PROXIMO ONIBUS?", "Recomendo conferir no aplicativo ou site da empresa de transporte local.");
        respostas.put("ME LEMBRE DE BEBER AGUA", "Boa ideia! Manter-se hidratado é importante!");
        respostas.put("QUAL A MINHA AGENDA DE HOJE?", "No momento, não posso acessar sua agenda, mas você pode verificar no seu aplicativo de calendário.");
        respostas.put("QUE HORAS SAO?", "Recomendo verificar o horário no relógio do seu dispositivo.");
        respostas.put("ME ACORDE AS 7 DA MANHA", "Não posso definir alarmes, mas lembre-se de configurar um no seu celular!");
        respostas.put("ME CONTE UMA CURIOSIDADE", "Sabia que as abelhas comunicam umas com as outras através de danças? Incrível, né?");
        respostas.put("QUAL E A CAPITAL DA FRANÇA?", "A capital da França é Paris.");
        respostas.put("QUANTOS PLANETAS TEM NO SISTEMA SOLAR?", "O sistema solar possui oito planetas: Mercúrio, Vênus, Terra, Marte, Júpiter, Saturno, Urano e Netuno.");
        respostas.put("O QUE E A TEORIA DA RELATIVIDADE?", "A teoria da relatividade, proposta por Einstein, explica como o espaço e o tempo são relativos e como a gravidade afeta ambos.");
        respostas.put("QUAL E A IMPORTANCIA DE EXERCITAR-SE?", "Exercitar-se ajuda na saúde física e mental, reduz o estresse e melhora a qualidade de vida.");
        respostas.put("QUANTO TEMPO DEVO DORMIR?", "O ideal para um adulto é dormir entre 7 a 9 horas por noite.");
        respostas.put("QUAL E A IMPORTANCIA DE UMA ALIMENTAÇAO SAUDAVEL?", "Uma alimentação saudável fornece nutrientes essenciais, melhora a disposição e previne doenças.");
        respostas.put("QUAL FILME VOCE RECOMENDA?", "Que tal um clássico como 'O Poderoso Chefão' ou uma animação como 'Divertida Mente'?");
        respostas.put("VOCE TEM DICAS DE LIVROS?", "Sim! '1984' de George Orwell e 'Dom Quixote' de Miguel de Cervantes são excelentes leituras.");
        respostas.put("O QUE POSSO FAZER PARA ME DIVERTIR?", "Experimente um novo hobby, como pintar, ouvir música, assistir a um bom filme ou sair para uma caminhada!");
        respostas.put("QUAL E O SIGNIFICADO DA VIDA?", "Essa é uma grande pergunta! Alguns dizem que é encontrar a felicidade e fazer o bem para os outros.");
        respostas.put("VOCÊ GOSTA DE MUSICA?", "Sim! Música é uma ótima forma de relaxar e se divertir.");
        respostas.put("ME CONTE UMA PIADA", "Claro! Por que o livro de matemática se suicidou? Porque tinha muitos problemas!");
        respostas.put("VOCE PODE ME AJUDAR COM ALGUMA COISA?", "Claro, estou aqui para ajudar! Pergunte o que quiser.");
        respostas.put("O QUE E INTELIGENCIA ARTIFICIAL?", "Inteligência Artificial é a capacidade de uma máquina de simular habilidades humanas como raciocínio e aprendizado.");
        respostas.put("O QUE E UM ALGORITMO?", "Um algoritmo é uma sequência de passos lógicos para resolver um problema.");
        respostas.put("COMO POSSO MELHORAR MINHA CONEXAO DE INTERNET?", "Tente reiniciar o roteador e verificar se há muitos dispositivos conectados. Evite usar a internet de outros aparelhos enquanto realiza atividades de alta demanda.");
        respostas.put("O QUE E NUVEM?", "A nuvem é uma tecnologia que permite armazenar e acessar dados pela internet, sem depender de dispositivos físicos de armazenamento.");
        respostas.put("O QUE E UM BUG?", "Bug é um erro ou falha em um programa ou sistema que causa um comportamento inesperado.");
        respostas.put("COMO SE DIZ 'OBRIGADO' EM FRANCES?", "Em francês, diz-se 'Merci'.");
        respostas.put("COMO SE DIZ 'EU TE AMO' EM INGLES?", "Em inglês, diz-se 'I love you'.");
        respostas.put("O QUE E UMA FESTA JUNINA?", "A Festa Junina é uma celebração tradicional brasileira em homenagem aos santos populares, com comidas típicas, danças e roupas caipiras.");
        respostas.put("O QUE E O HANAMI NO JAPAO?", "Hanami é a tradição japonesa de apreciar a beleza das flores, especialmente as flores de cerejeira, na primavera.");
        respostas.put("VOCE TEM UMA FRASE MOTIVACIONAL?", "Claro! 'O sucesso é a soma de pequenos esforços repetidos dia após dia.'");
        respostas.put("COMO POSSO LIDAR COM O ESTRESSE?", "Experimente técnicas de respiração, meditação e reserve um tempo para atividades que você gosta.");
        respostas.put("O QUE POSSO FAZER PARA MELHORAR MINHA PRODUTIVIDADE?", "Faça listas de tarefas, priorize o mais importante e evite distrações como redes sociais durante o trabalho.");
        respostas.put("COMO POSSO MELHORAR MINHA AUTOESTIMA?", "Reconheça suas qualidades, celebre suas conquistas e não se compare aos outros.");
        respostas.put("VOCE PODE ME DAR UM CONSELHO DE VIDA?", "Aproveite cada momento e invista seu tempo nas pessoas e atividades que você ama.");
        respostas.put("QUAL A MELHOR FORMA DE ESTUDAR?", "A melhor forma é organizar o conteúdo, estudar em blocos de tempo e fazer pausas para absorver melhor a informação.");
        respostas.put("COMO MEMORIZAR MAIS FACIL?", "Use técnicas de associação, mapas mentais e revise o conteúdo regularmente.");
        respostas.put("QUAL A IMPORTANCIA DA LEITURA?", "Ler amplia o vocabulário, melhora a concentração e nos permite aprender coisas novas.");
        respostas.put("COMO MELHORAR MEU INGLES?", "Assista filmes, ouça músicas e pratique conversação sempre que possível.");
        respostas.put("VOCE TEM DICAS PARA PROVAS?", "Sim! Durma bem antes da prova, revise o conteúdo com antecedência e controle o tempo durante a prova.");
        respostas.put("QUAL O MELHOR MOMENTO PARA VIAJAR?", "Depende do destino, mas evitar a alta temporada pode garantir preços melhores e menos filas.");
        respostas.put("O QUE LEVAR EM UMA VIAGEM?", "Leve roupas adequadas ao clima, documentos, produtos de higiene e um kit de primeiros socorros.");
        respostas.put("QUAIS SAO OS DESTINOS MAIS POPULARES?", "Destinos populares incluem Paris, Tóquio, Rio de Janeiro, Nova York e Londres.");
        respostas.put("COMO EVITAR O JET LAG?", "Tente se ajustar ao fuso horário do destino antes de viajar e descanse bem.");
        respostas.put("VOCE TEM DICAS PARA ECONOMIZAR EM VIAGENS?", "Sim! Compre passagens com antecedência, pesquise opções de hospedagem alternativas e evite comer em pontos turísticos.");
        respostas.put("QUAL E O ANIMAL MAIS RAPIDO DO MUNDO?", "O animal mais rápido do mundo é o falcão-peregrino, que pode atingir até 320 km/h em voo.");
        respostas.put("O QUE E O BIG BANG?", "O Big Bang é a teoria que explica a origem do universo, ocorrido há cerca de 13,8 bilhões de anos.");
        respostas.put("QUANTO TEMPO A LUZ DEMORA PARA CHEGAR DO SOL ATE A TERRA?", "A luz do Sol leva cerca de 8 minutos e 20 segundos para chegar até a Terra.");
        respostas.put("QUAL E A LINGUAGEM DE PROGRAMAÇAO MAIS USADA?", "Algumas das mais usadas são Python, JavaScript, Java e C++.");
        respostas.put("QUAL SEU NOME?", "Eu sou um bot criado para ajudar você!");
        respostas.put("ONDE VOCE MORA?", "Eu não tenho um lugar fixo, mas estou aqui para te ajudar sempre que precisar.");
        respostas.put("O QUE VOCE FAZ?", "Eu respondo às suas perguntas e tento ajudar no que for possível.");
        respostas.put("QUAL E O SEU OBJETIVO?", "Meu objetivo é tornar sua experiência mais fácil, respondendo às suas dúvidas.");
        respostas.put("VOCE GOSTA DE JOGAR?", "Eu não posso jogar, mas posso te ajudar a encontrar jogos interessantes!");
        respostas.put("O QUE E UM BOT?", "Um bot é um programa que pode automatizar tarefas, como responder perguntas.");
        respostas.put("QUAL A DIFERENÇA ENTRE UM ROBO E UM BOT?", "Um robô é uma máquina física, enquanto um bot é um software que realiza tarefas online.");
        respostas.put("VOCE SABE PROGRAMAR?", "Sim, fui programado para entender algumas linguagens de programação e responder perguntas sobre elas.");
        respostas.put("O QUE E INTELIGÊNCIA ARTIFICIAL?", "Inteligência Artificial é a capacidade das máquinas de aprender e executar tarefas normalmente feitas por humanos.");
        respostas.put("COMO POSSO APRENDER A PROGRAMAR?", "Existem muitos recursos online como tutoriais, cursos e livros. Eu posso te ajudar a encontrar alguns.");
        respostas.put("VOCE PODE ME AJUDAR COM MATEMATICA?", "Claro! Posso ajudar a resolver equações, converter unidades e muito mais.");
        respostas.put("QUAIS SAO OS BENEFÍCIOS DA TECNOLOGIA?", "A tecnologia pode melhorar a eficiência, facilitar o acesso à informação e resolver problemas complexos.");
        respostas.put("QUAL O FUTURO DA TECNOLOGIA?", "O futuro é promissor, com avanços em inteligência artificial, computação quântica e muito mais.");
        respostas.put("O QUE E A INTERNET DAS COISAS?", "A Internet das Coisas é a conexão de dispositivos físicos à internet, permitindo que se comuniquem entre si.");
        respostas.put("O QUE E UM CHATBOT?", "Um chatbot é um programa de computador projetado para simular conversas com seres humanos.");
        respostas.put("QUAIS SAO AS CORES DA BANDEIRA DO BRASIL?", "As cores da bandeira do Brasil são verde, amarelo, azul e branco.");
        respostas.put("QUAL E O MAIOR ESTADO DO BRASIL?", "O maior estado do Brasil é o Amazonas.");
        respostas.put("QUEM FOI O PRIMEIRO PRESIDENTE DO BRASIL?", "O primeiro presidente do Brasil foi Marechal Deodoro da Fonseca.");
        respostas.put("O QUE E O CRISTO REDENTOR?", "O Cristo Redentor é uma estátua de Jesus Cristo localizada no Rio de Janeiro, considerada uma das sete maravilhas do mundo moderno.");
        respostas.put("QUAL E O MAIOR RIO DO BRASIL?", "O maior rio do Brasil é o Amazonas.");
        respostas.put("QUAL E A CIDADE MAIS POPULOSA DO BRASIL?", "A cidade mais populosa do Brasil é São Paulo.");
        respostas.put("O QUE E A AMAZONIA?", "A Amazônia é a maior floresta tropical do mundo, localizada principalmente no Brasil, e é essencial para o equilíbrio ecológico do planeta.");
        respostas.put("QUAL E A MAIOR CIDADE DO MUNDO?", "A maior cidade do mundo em termos de população é Tóquio, no Japão.");
        respostas.put("QUANDO O BRASIL SE TORNOU INDEPENDENTE?", "O Brasil se tornou independente de Portugal no dia 7 de setembro de 1822.");
        respostas.put("O QUE E A ONU?", "A ONU (Organização das Nações Unidas) é uma organização internacional fundada em 1945 para promover a paz, segurança e direitos humanos no mundo.");
        respostas.put("QUAL E O PAIS MAIS RICO DO MUNDO?", "O país mais rico do mundo, em termos de PIB per capita, é Luxemburgo.");
        respostas.put("QUAL E A MOEDA DO REINO UNIDO?", "A moeda do Reino Unido é a libra esterlina (GBP).");
        respostas.put("QUAL E O PAIS ONDE NASCEU O NAPOLÉON BONAPARTE?", "Napoléon Bonaparte nasceu na Córsega, que na época era uma região pertencente à França.");
        respostas.put("O QUE E O MURRO DE BERLIM?", "O Muro de Berlim foi uma barreira física que separava a Alemanha Oriental da Alemanha Ocidental durante a Guerra Fria.");
        respostas.put("QUAL E A MONTANHA MAIS ALTA DO MUNDO?", "A montanha mais alta do mundo é o Monte Everest, localizado na fronteira entre o Nepal e o Tibete.");
        respostas.put("QUAIS SAO OS PAISES DA AMERICA DO SUL?", "Os países da América do Sul são: Argentina, Brasil, Chile, Colômbia, Equador, Guiana, Paraguai, Peru, Suriname, Uruguai e Venezuela.");
        respostas.put("QUANDO FOI A SEGUNDA GUERRA MUNDIAL?", "A Segunda Guerra Mundial aconteceu entre 1939 e 1945.");
        respostas.put("QUEM FOI ALBERT EINSTEIN?", "Albert Einstein foi um físico teórico, conhecido por desenvolver a teoria da relatividade, uma das duas principais teorias da física moderna.");
        respostas.put("O QUE E O VATICANO?", "O Vaticano é um pequeno país independente localizado em Roma, Itália, sendo o centro da Igreja Católica e a residência do Papa.");
        respostas.put("O QUE E A MURALHA DA CHINA?", "A Muralha da China é uma série de fortificações construídas ao longo de mais de 2.000 km, com o objetivo de proteger o Império Chinês contra invasões.");
        respostas.put("QUAL E O PAIS MAIS QUENTE DO MUNDO?", "O país mais quente do mundo é o Kuwait, onde foram registradas as mais altas temperaturas já registradas.");
        respostas.put("QUAL E O MAIOR PAIS EM EXTENSÃO TERRITORIAL?", "O maior país em extensão territorial é a Rússia.");
        respostas.put("QUAL E A LÍNGUA MAIS FALADA NO MUNDO?", "A língua mais falada no mundo é o Mandarim, falado principalmente na China.");
        respostas.put("QUAL E O MAIOR OCEANO DO MUNDO?", "O maior oceano do mundo é o Oceano Pacífico.");
        respostas.put("O QUE E O PROTOCOLO DE KYOTO?", "O Protocolo de Kyoto é um tratado internacional que visa combater as mudanças climáticas, reduzindo as emissões de gases do efeito estufa.");
        respostas.put("QUAL E A CIDADE MAIS VISITADA DO MUNDO?", "A cidade mais visitada do mundo é Bangkok, na Tailândia.");
        respostas.put("QUEM DESCUBRIU O BRASIL?", "O Brasil foi oficialmente 'descoberto' por Pedro Álvares Cabral em 1500.");
        respostas.put("QUEM FOI LEONARDO DA VINCI?", "Leonardo da Vinci foi um polímata italiano, conhecido por suas obras de arte como a Mona Lisa e a Última Ceia, além de seus estudos em diversas áreas como anatomia e engenharia.");
        respostas.put("O QUE E A REVOLUÇÃO INDUSTRIAL?", "A Revolução Industrial foi um período de grandes transformações econômicas e tecnológicas que começou no final do século XVIII e mudou a produção de bens e a organização social.");
        respostas.put("QUAL e O GÊNERO MUSICAL MAIS POPULAR NO BRASIL?", "O gênero musical mais popular no Brasil é o samba, mas outros gêneros como bossa nova, sertanejo e funk também são muito populares.");
        respostas.put("QUEM FOI BEETHOVEN?", "Ludwig van Beethoven foi um compositor e pianista alemão, um dos maiores e mais influentes compositores da música clássica.");
        respostas.put("O QUE E A MUSICA MPB?", "MPB (Música Popular Brasileira) é um gênero musical que mistura elementos da música tradicional brasileira com influências de jazz, rock, e música clássica.");
        respostas.put("QUAL E O MAIOR ALBUM DE TODOS OS TEMPOS?", "O álbum 'Abbey Road' dos Beatles é frequentemente considerado um dos maiores álbuns de todos os tempos.");
        respostas.put("QUEM SAO OS BEATLES?", "Os Beatles foram uma banda de rock britânica formada em Liverpool, composta por John Lennon, Paul McCartney, George Harrison e Ringo Starr.");
        respostas.put("QUEM E MICHAEL JACKSON?", "Michael Jackson foi um cantor, compositor e dançarino americano, considerado o 'Rei do Pop'. Ele é um dos artistas mais vendidos de todos os tempos.");
        respostas.put("O QUE E O ROCK?", "O rock é um gênero musical que se originou nos Estados Unidos na década de 1950, caracterizado por uma forte presença de guitarra elétrica, bateria e baixo.");
        respostas.put("O QUE E A BOSSA NOVA?", "A Bossa Nova é um gênero musical brasileiro que surgiu no final da década de 1950, misturando samba com jazz e tendo artistas como João Gilberto e Tom Jobim como ícones.");
        respostas.put("QUEM E FREDDIE MERCURY?", "Freddie Mercury foi o vocalista e líder da banda de rock Queen, conhecido por sua voz poderosa e presença de palco extraordinária.");
        respostas.put("QUAL E O MAIOR FESTIVAL DE MUSICA DO MUNDO?", "O maior festival de música do mundo é o Festival de Glastonbury, realizado anualmente no Reino Unido.");
        respostas.put("O QUE E A MÚSICA CLASSICA?", "A música clássica é um gênero que se desenvolveu entre os séculos XVII e XIX, com compositores como Bach, Mozart e Beethoven. Ela é caracterizada pela complexidade e sofisticação das obras.");
        respostas.put("QUAL E O ESTILO MUSICAL DE ELVIS PRESLEY?", "Elvis Presley foi conhecido como o 'Rei do Rock' e misturou rock and roll, blues, gospel e música country em suas músicas.");
        respostas.put("O QUE E A MUSICA FOLK?", "Folk é um gênero musical tradicional que tem suas raízes na música popular de várias culturas, e é caracterizado por letras simples e melodias suaves.");
        respostas.put("O QUE E O JAZZ?", "O jazz é um gênero musical originado nos Estados Unidos no início do século XX, caracterizado pela improvisação, ritmo sincopado e influência de blues e música africana.");
        respostas.put("O QUE E O REGGAE?", "O reggae é um gênero musical originado na Jamaica, com ritmo característico e mensagens de paz, amor e resistência social, sendo Bob Marley um de seus maiores representantes.");
        respostas.put("QUEM FOI ELVIS PRESLEY?", "Elvis Presley foi um cantor, ator e ícone cultural americano, conhecido como o 'Rei do Rock', cujas músicas e estilo transformaram a música popular.");
        respostas.put("O QUE E A MUSICA ELECTRONICA?", "A música eletrônica é um gênero musical produzido utilizando instrumentos eletrônicos e tecnologia digital, e é muito popular em festas e clubes de dança.");
        respostas.put("QUAL E O MAIOR FESTIVAL DE MUSICA ELETRONICA DO MUNDO?", "O maior festival de música eletrônica do mundo é o Tomorrowland, realizado anualmente na Bélgica.");
        respostas.put("O QUE E O FADO?", "O fado é um gênero musical português, conhecido por suas músicas melancólicas e poéticas, geralmente interpretadas com guitarra portuguesa.");
        respostas.put("O QUE E A MÚSICA POP?", "Música pop (ou pop music) é um gênero musical popular que é caracterizado por suas melodias acessíveis e por ser amplamente consumido pelo público em geral. Artistas como Michael Jackson, Madonna e Beyoncé são ícones do pop.");
        respostas.put("QUEM FOI CHICO BUARQUE?", "Chico Buarque é um dos maiores músicos e compositores brasileiros, famoso por sua obra na MPB, com músicas que abordam temas como política, amor e sociedade.");
        respostas.put("QUAL E O MAIOR CONCERTO DE MÚSICA JA REALIZADO?", "O maior concerto de música já realizado foi um show de Rod Stewart em Copacabana, Rio de Janeiro, em 1994, com cerca de 3,5 milhões de pessoas.");
        respostas.put("O QUE E O PUNK?", "O punk é um gênero musical que surgiu nos anos 1970 e é caracterizado por músicas rápidas, simples e com letras provocativas. A estética punk também envolve rebeldia e atitudes contestadoras.");
        respostas.put("QUEM E TAYLOR SWIFT?", "Taylor Swift é uma cantora e compositora americana conhecida por sua música pop e country, com grandes sucessos como 'Love Story' e 'Shake It Off'.");
        respostas.put("O QUE E O BLUES?", "O blues é um gênero musical originado na comunidade afro-americana no sul dos Estados Unidos, caracterizado por sua estrutura melódica simples e letras que expressam a melancolia e as dificuldades da vida.");
        respostas.put("O QUE E A MUSICA INDIE?", "A música indie é um gênero musical associado a artistas independentes, que optam por não assinar com grandes gravadoras e mantêm controle criativo sobre suas produções.");
        respostas.put("O QUE E A MUSICA LATINA?", "A música latina engloba uma variedade de estilos originários da América Latina, como salsa, bachata, reggaeton e cumbia, e tem uma forte influência no cenário musical global.");
        respostas.put("QUAL E O TIME DE FUTEBOL MAIS VENCEDOR DO MUNDO?", "O Real Madrid é o time de futebol mais vencedor do mundo, com várias conquistas da Liga dos Campeões da UEFA e outros títulos importantes.");
        respostas.put("QUEM E O MAIOR JOGADOR DE FUTEBOL DE TODOS OS TEMPOS?", "O debate sobre o maior jogador de futebol de todos os tempos envolve nomes como Pelé, Diego Maradona, Lionel Messi e Cristiano Ronaldo.");
        respostas.put("O QUE E A COPA DO MUNDO DE FUTEBOL?", "A Copa do Mundo de Futebol é o maior torneio internacional de futebol, realizado a cada quatro anos, organizado pela FIFA, com seleções de todo o mundo competindo.");
        respostas.put("QUAL E O MAIOR TIME DE FUTEBOL DO BRASIL?", "O maior time de futebol do Brasil é discutido entre clubes como Flamengo, Palmeiras, São Paulo e Corinthians, com base em títulos conquistados.");
        respostas.put("QUEM FOI PELE?", "Pelé é considerado um dos maiores jogadores de futebol de todos os tempos, tendo sido campeão mundial três vezes com a seleção brasileira e um ícone global do esporte.");
        respostas.put("QUAL E O MAIOR ESTÁDIO DE FUTEBOL DO BRASIL?", "O maior estádio de futebol do Brasil é o Maracanã, localizado no Rio de Janeiro, com capacidade para mais de 78.000 espectadores.");
        respostas.put("QUEM GANHOU A COPA DO MUNDO DE 2018?", "A Copa do Mundo de 2018 foi vencida pela França, que derrotou a Croácia por 4 a 2 na final.");
        respostas.put("O QUE E O CLASSIFICATORIO DA COPA DO MUNDO?", "O Classificatório da Copa do Mundo é uma fase eliminatória que determina as seleções que se classificam para o torneio principal da Copa do Mundo.");
        respostas.put("QUEM E CRISTIANO RONALDO?", "Cristiano Ronaldo é um dos maiores jogadores de futebol da história, com inúmeros títulos, incluindo a Liga dos Campeões da UEFA e o prêmio de Melhor Jogador do Mundo da FIFA.");
        respostas.put("QUEM GANHOU A COPA DO MUNDO DE 1998?", "A Copa do Mundo de 1998 foi vencida pela França, que derrotou o Brasil por 3 a 0 na final, conquistando seu primeiro título mundial.");
        respostas.put("O QUE E A LIBERTADORES DA AMÉRICA?", "A Copa Libertadores da América é o torneio de clubes mais importante da América do Sul, onde os melhores times de futebol da região competem pela supremacia continental.");
        respostas.put("O QUE E O BOLA DE OURO?", "A Bola de Ouro é um prêmio anual concedido pela FIFA ao melhor jogador de futebol do mundo. Os vencedores incluem jogadores como Lionel Messi, Cristiano Ronaldo e Neymar.");
        respostas.put("QUEM E ZICO?", "Zico é um dos maiores ícones do futebol brasileiro, famoso por sua habilidade técnica, passes precisos e seu papel importante na seleção brasileira e no Flamengo.");
        respostas.put("QUAL E A SELEÇAO DE FUTEBOL MAIS VITORIOSA?", "A seleção de futebol mais vitoriosa da história da Copa do Mundo é a seleção do Brasil, com cinco títulos conquistados: 1958, 1962, 1970, 1994 e 2002.");
        respostas.put("O QUE E O VAR?", "O VAR (Árbitro Assistente de Vídeo) é uma tecnologia utilizada no futebol para ajudar a equipe de arbitragem a tomar decisões mais precisas, revisando lances polêmicos durante o jogo.");
        respostas.put("O QUE E O FUTEBOL FEMININO?", "O futebol feminino é a modalidade de futebol jogada por mulheres. Embora tenha crescido muito nos últimos anos, ainda busca maior visibilidade e reconhecimento.");
        respostas.put("QUEM FOI DIEGO MARADONA?", "Diego Maradona foi um dos maiores jogadores de futebol da história, famoso por sua habilidade técnica e liderança, sendo ícone da seleção argentina e do futebol mundial.");
        respostas.put("O QUE E O CAMPEONATO BRASILEIRO?", "O Campeonato Brasileiro, também conhecido como Brasileirão, é a principal competição de futebol do Brasil, envolvendo clubes de todas as partes do país.");
        respostas.put("QUEM FOI RONALDO FENOMENO?", "Ronaldo Fenômeno é um ex-jogador de futebol brasileiro, considerado um dos melhores atacantes da história do esporte, vencedor de dois prêmios da FIFA de Melhor Jogador do Mundo.");
        respostas.put("O QUE E A COPA AMERICA?", "A Copa América é o torneio de seleções mais antigo da América do Sul, com equipes como Brasil, Argentina, Chile e Uruguai competindo pelo título continental.");
        respostas.put("QUEM E MESSI?", "Lionel Messi é um dos maiores jogadores de futebol da história, com inúmeras vitórias, incluindo múltiplas Bolas de Ouro e títulos com o Barcelona e a seleção argentina.");
        respostas.put("O QUE E A PREMIER LEAGUE?", "A Premier League é a principal competição de futebol da Inglaterra, considerada uma das ligas mais competitivas e assistidas do mundo.");
        respostas.put("QUEM GANHOU A COPA DO MUNDO DE 2014?", "A Copa do Mundo de 2014 foi vencida pela Alemanha, que derrotou a Argentina por 1 a 0 na final, com um gol de Mario Götze.");
        respostas.put("O QUE E O FUTEBOL DE BASE?", "O futebol de base refere-se aos níveis de futebol juvenil e formativo, onde os jovens jogadores começam sua trajetória profissional e desenvolvem habilidades.");
        respostas.put("O QUE E O 'FUTEBOL EUROPEU'?", "O futebol europeu é composto pelas principais ligas e competições de clubes do continente europeu, incluindo a Liga dos Campeões da UEFA e as ligas nacionais como a Premier League, La Liga e Serie A.");
        respostas.put("QUEM E O MAIOR JOGADOR DE BASQUETE DE TODOS OS TEMPOS?", "O debate sobre o maior jogador de basquete de todos os tempos envolve nomes como Michael Jordan, LeBron James, Kobe Bryant e Magic Johnson.");
        respostas.put("O QUE E A NBA?", "A NBA (National Basketball Association) é a principal liga de basquete profissional dos Estados Unidos, considerada a melhor liga de basquete do mundo.");
        respostas.put("QUEM GANHOU A NBA EM 2020?", "O Los Angeles Lakers venceu a NBA em 2020, derrotando o Miami Heat nas finais, com LeBron James sendo o MVP das finais.");
        respostas.put("O QUE E O ALL-STAR GAME?", "O All-Star Game é um jogo anual de exibição na NBA, onde os melhores jogadores da temporada são escolhidos para representar suas conferências em uma partida de celebridades.");
        respostas.put("QUEM FOI MICHAEL JORDAN?", "Michael Jordan é amplamente considerado o maior jogador de basquete da história, com seis títulos da NBA com o Chicago Bulls e cinco prêmios de MVP das finais.");
        respostas.put("O QUE E A LIGA NACIONAL DE BASQUETE DO BRASIL?", "A Liga Nacional de Basquete (LNB) é a principal competição de basquete do Brasil, onde equipes brasileiras competem pelo título nacional.");
        respostas.put("QUEM FOI KOBE BRYANT?", "Kobe Bryant foi um dos maiores jogadores de basquete de todos os tempos, conhecido por sua habilidade, ética de trabalho e por conquistar cinco títulos da NBA com o Los Angeles Lakers.");
        respostas.put("O QUE E UM TRIPLE-DOUBLE?", "Um triple-double é uma estatística rara no basquete, onde um jogador alcança dois dígitos em três diferentes categorias (pontos, assistências, rebotes, etc.) em um único jogo.");
        respostas.put("QUEM FOI MAGIC JOHNSON?", "Magic Johnson foi um dos maiores armadores da NBA, famoso por sua visão de jogo e habilidades de passe, tendo vencido cinco títulos da NBA com o Los Angeles Lakers.");
        respostas.put("O QUE E O DRAFT DA NBA?", "O Draft da NBA é um evento anual onde as equipes escolhem novos jogadores, geralmente de universidades, para reforçar seus elencos.");
        respostas.put("QUEM FOI LEBRON JAMES?", "LeBron James é um dos maiores jogadores de basquete da história, conhecido por seu domínio em quadra, sendo quatro vezes campeão da NBA e MVP das finais em quatro ocasiões.");
        respostas.put("O QUE E A FINAL DA NBA?", "A Final da NBA é a série melhor de sete jogos entre os campeões das conferências Leste e Oeste, onde é decidido o campeão da liga de basquete dos EUA.");
        respostas.put("O QUE E O JOGO DAS ESTRELAS?", "O Jogo das Estrelas é uma competição anual da NBA, onde jogadores de destaque da liga competem entre si em várias provas, incluindo o jogo principal entre as equipes da Conferência Leste e Oeste.");
        respostas.put("O QUE E O CONFERENCIA LESTE E OESTE?", "Na NBA, as equipes são divididas em duas conferências: Leste e Oeste. Cada conferência é composta por 15 times que competem entre si durante a temporada regular.");
        respostas.put("QUEM FOI WILT CHAMBERLAIN?", "Wilt Chamberlain foi um dos maiores jogadores da NBA, conhecido por sua habilidade de marcar pontos, inclusive sendo o único a fazer 100 pontos em um único jogo.");
        respostas.put("O QUE E O CAMPEONATO NACIONAL DE BASQUETE FEMININO?", "O Campeonato Nacional de Basquete Feminino é a principal competição de basquete para equipes femininas no Brasil, organizado pela LBF (Liga de Basquete Feminino).");
        respostas.put("O QUE E UM BASKET?", "O termo 'basket' refere-se à cesta, o principal objetivo do jogo de basquete, onde a bola deve ser arremessada para dentro da cesta do time adversário para marcar pontos.");
        respostas.put("QUEM GANHOU O CAMPEONATO MUNDIAL DE BASQUETE DE 2019?", "O Campeonato Mundial de Basquete de 2019 foi vencido pelos Estados Unidos, que derrotaram a França na final.");
        respostas.put("QUEM FOI SHAQUILLE O'NEAL?", "Shaquille O'Neal foi um dos jogadores mais dominantes da NBA, conhecido por sua força física e habilidades no garrafão, ganhando quatro títulos da NBA.");
        respostas.put("O QUE E O TOCO NO BASQUETE?", "O toco é uma ação defensiva no basquete em que o jogador bloqueia o arremesso do adversário, impedindo que a bola entre na cesta.");
        respostas.put("QUEM FOI DIRK NOWITZKI?", "Dirk Nowitzki é um ex-jogador de basquete da NBA, conhecido por sua habilidade no arremesso de longa distância, tendo vencido o título da NBA com o Dallas Mavericks em 2011.");
        respostas.put("O QUE E O LAYUP?", "O layup é um tipo de arremesso no basquete em que o jogador aproxima-se da cesta e, com uma mão, tenta fazer a bola entrar com um toque suave.");
        respostas.put("O QUE E O GESTO DE MAO NO BASQUETE?", "O gesto de mão no basquete refere-se ao ato de segurar a bola corretamente, usando os dedos para ter mais controle e precisão durante os passes e arremessos.");
        respostas.put("O QUE E UM ARREMESSO DE 3 PONTOS?", "Um arremesso de 3 pontos é feito quando um jogador arremessa a bola de fora da linha de 3 pontos, marcando três pontos ao invés de dois caso a cesta seja convertida.");
        respostas.put("O QUE E O PIVO?", "O pivô é a posição no basquete que se caracteriza por um jogador alto e forte, geralmente responsável por pegar rebotes e defender a área do garrafão.");
        respostas.put("O QUE E O GARRAFAO?", "O garrafão é a área delimitada ao redor da cesta, onde as jogadas mais intensas, como rebotes e bloqueios, geralmente ocorrem.");
        respostas.put("O QUE E O SISTEMA DE JOGO DA NBA?", "O sistema de jogo da NBA é caracterizado por um ritmo rápido, com ênfase no arremesso de longa distância, passes rápidos e transições velozes entre ataque e defesa.");
        respostas.put("O QUE E O BANCO DE RESERVAS NO BASQUETE?", "O banco de reservas é o local onde ficam os jogadores que não estão em quadra no momento. Eles podem ser chamados para entrar durante o jogo para substituir os titulares.");
        respostas.put("QUANDO A SEGUNDA GUERRA MUNDIAL COMEÇOU?", "A Segunda Guerra Mundial começou em 1º de setembro de 1939, com a invasão da Polônia pela Alemanha.");
        respostas.put("QUEM FOI MAHATMA GANDHI?", "Mahatma Gandhi foi um líder indiano que lutou pela independência da Índia do domínio britânico usando métodos de resistência pacífica.");
        respostas.put("O QUE FOI A REVOLUÇAO FRANCESA?", "A Revolução Francesa foi um período de grandes mudanças políticas e sociais na França, que começou em 1789, e levou ao fim da monarquia e ao estabelecimento de uma república.");
        respostas.put("O QUE FOI A GUERRA FRIA?", "A Guerra Fria foi um período de tensões políticas e militares entre os Estados Unidos e a União Soviética, sem um confronto direto, que ocorreu entre 1947 e 1991.");
        respostas.put("O QUE FOI A INDEPENDENCIA DOS ESTADOS UNIDOS?", "A independência dos Estados Unidos foi declarada em 4 de julho de 1776, com a assinatura da Declaração de Independência, que separou os EUA da Grã-Bretanha.");
        respostas.put("QUANDO A REPUBLICA ROMANA FOI FUNDADA?", "A República Romana foi fundada em 509 a.C., após a derrubada da monarquia, e durou até 27 a.C., quando se transformou no Império Romano.");
        respostas.put("O QUE FOI A QUEDA DO MURRO DE BERLIM?", "A Queda do Muro de Berlim ocorreu em 9 de novembro de 1989 e marcou o fim da divisão da Alemanha e o colapso do regime comunista na Europa Oriental.");
        respostas.put("QUEM FOI NAPOLEAO BONAPARTE?", "Napoleão Bonaparte foi um líder militar e imperador francês que conquistou grande parte da Europa no início do século XIX e é conhecido por seu código civil, o Código Napoleônico.");
        respostas.put("O QUE FOI A GUERRA DO VIETNA?", "A Guerra do Vietnã foi um conflito armado entre o Vietnã do Norte, apoiado pela União Soviética e China, e o Vietnã do Sul, apoiado pelos Estados Unidos, ocorrida entre 1955 e 1975.");
        respostas.put("O QUE FOI A REVOLUÇAO INDUSTRIAL?", "A Revolução Industrial foi um período de grandes inovações tecnológicas e transformações econômicas, que começou na Grã-Bretanha no final do século XVIII e se espalhou para o resto do mundo.");
        respostas.put("QUEM FOI JULIUS CAESAR?", "Júlio César foi um general e estadista romano, famoso por suas conquistas militares e pela ditadura que ele estabeleceu antes de ser assassinado em 44 a.C.");
        respostas.put("QUANDO A AMERICA FOI DESCUBERTA?", "Cristóvão Colombo chegou ao continente americano em 12 de outubro de 1492, embora já existissem outros povos na América antes de sua chegada.");
        respostas.put("O QUE FOI O IMPERIO ROMANO?", "O Império Romano foi uma das maiores civilizações da história, que dominou vastas áreas da Europa, Norte da África e Oriente Médio, entre 27 a.C. e 476 d.C.");
        respostas.put("QUEM FOI CLEOPATRA?", "Cleópatra foi a última rainha do Egito Ptolemaico e conhecida por sua inteligência e influência, tendo sido amante de Júlio César e casada com Marco Antônio.");
        respostas.put("O QUE FOI O IMPERIO OTOMANO?", "O Império Otomano foi um império que durou de 1299 a 1922, abrangendo grande parte do Sudeste da Europa, Ásia Menor, Norte da África e Oriente Médio.");
        respostas.put("O QUE FOI O MARCO ZERO DE BRASILIA?", "O Marco Zero de Brasília é o ponto de origem da cidade e foi estabelecido para marcar o centro geográfico da nova capital do Brasil, inaugurada em 1960.");
        respostas.put("QUANDO A PRIMEIRA GUERRA MUNDIAL ACONTECEU?", "A Primeira Guerra Mundial ocorreu de 1914 a 1918, envolvendo várias potências europeias em um conflito devastador que alterou o mapa político mundial.");
        respostas.put("O QUE FOI A CONQUISTA DO ESPAÇO?", "A Conquista do Espaço é o nome dado ao período de avanços tecnológicos e exploração espacial que começou na década de 1950, com a corrida espacial entre os EUA e a União Soviética.");
        respostas.put("QUEM FOI ABRAHAM LINCOLN?", "Abraham Lincoln foi o 16º presidente dos Estados Unidos, famoso por sua liderança durante a Guerra Civil Americana e pela abolição da escravidão.");
        respostas.put("O QUE FOI A REFORMA PROTESTANTE?", "A Reforma Protestante foi um movimento religioso iniciado por Martinho Lutero em 1517, que resultou na divisão da Igreja Católica e no surgimento das igrejas protestantes.");
        respostas.put("O QUE FOI A EXPLORAÇÃO MARITIMA PORTUGUESA?", "A Exploração Marítima Portuguesa foi uma série de viagens realizadas no século XV e XVI, com o objetivo de expandir o império português e descobrir novas rotas comerciais.");
        respostas.put("O QUE FOI A MORTE DE SOCRATES?", "Sócrates, um dos filósofos mais importantes da Grécia Antiga, foi condenado à morte em 399 a.C. por corromper a juventude e questionar as crenças tradicionais de Atenas.");
        respostas.put("O QUE FOI A RENASCENÇA?", "A Renascença foi um movimento cultural que ocorreu entre os séculos XIV e XVII, caracterizado por um renascimento do interesse nas artes, na ciência e na literatura da Antiguidade.");
        respostas.put("O QUE FOI A GUERRA CIVIL AMERICANA?", "A Guerra Civil Americana foi um conflito interno nos Estados Unidos, entre 1861 e 1865, principalmente sobre a questão da escravidão, que terminou com a vitória da União.");
        respostas.put("QUEM FOI SIMON BOLIVAR?", "Simón Bolívar foi um líder revolucionário latino-americano que ajudou a libertar vários países da América do Sul do domínio espanhol.");
        respostas.put("QUAL E O MAIOR PAIS DO MUNDO EM AREA?", "O maior país do mundo em área é a Rússia, com cerca de 17 milhões de quilômetros quadrados.");
        respostas.put("QUAL E O MENOR PAIS DO MUNDO?", "O menor país do mundo é o Vaticano, com apenas 0,44 quilômetros quadrados.");
        respostas.put("QUAL E O RIO MAIS LONGO DO MUNDO?", "O rio mais longo do mundo é o Rio Nilo, na África, com aproximadamente 6.650 quilômetros.");
        respostas.put("QUAL E A MAIOR MONTANHA DO MUNDO?", "A montanha mais alta do mundo é o Monte Everest, que tem 8.848 metros de altura.");
        respostas.put("ONDE FICA O DESERTO DO SAARA?", "O Deserto do Saara fica no norte da África e é o maior deserto quente do mundo.");
        respostas.put("QUANTOS CONTINENTES EXISTEM?", "Existem sete continentes: África, América do Norte, América do Sul, Ásia, Europa, Oceania e Antártida.");
        respostas.put("QUAL E O OCEANO MAIOR DO MUNDO?", "O maior oceano do mundo é o Oceano Pacífico, que cobre mais de 63 milhões de quilômetros quadrados.");
        respostas.put("QUAL E A CAPITAL DO BRASIL?", "A capital do Brasil é Brasília, inaugurada em 1960.");
        respostas.put("QUAL E O PAIS MAIS POPULOSO DO MUNDO?", "O país mais populoso do mundo é a China, com mais de 1,4 bilhão de habitantes.");
        respostas.put("QUAL E A MAIOR ILHA DO MUNDO?", "A maior ilha do mundo é a Groenlândia, com uma área de mais de 2 milhões de quilômetros quadrados.");
        respostas.put("ONDE FICA A FLORESTA AMAZONICA?", "A Floresta Amazônica está localizada na América do Sul, principalmente no Brasil, mas também se estende a outros países como Peru, Colômbia e Venezuela.");
        respostas.put("QUAL E O PAIS MAIS MONTANHOSO DO MUNDO?", "O Nepal é conhecido como o país mais montanhoso, pois abriga a maior parte da cordilheira do Himalaia, incluindo o Monte Everest.");
        respostas.put("QUAL E A CAPITAL DO JAPAO?", "A capital do Japão é Tóquio.");
        respostas.put("QUAL E O PAIS MAIS FRIO DO MUNDO?", "A Rússia é considerada um dos países mais frios do mundo, especialmente na região da Sibéria, onde as temperaturas podem cair abaixo de -50°C.");
        respostas.put("QUAL E O LAGO MAIS PROFUNDO DO MUNDO?", "O lago mais profundo do mundo é o Lago Baikal, na Rússia, com mais de 1.600 metros de profundidade.");
        respostas.put("QUAL E O MAIOR DESERTO DO MUNDO?", "O maior deserto do mundo é a Antártida, que é um deserto frio e cobre uma área de cerca de 14 milhões de quilômetros quadrados.");
        respostas.put("QUAIS SAO OS CINCO MAIORES PAISES DA AMERICA DO SUL?", "Os cinco maiores países da América do Sul em área são: Brasil, Argentina, Peru, Colômbia e Bolívia.");
        respostas.put("ONDE FICA A CORDILHEIRA DOS ANDES?", "A Cordilheira dos Andes está localizada na América do Sul, estendendo-se por vários países, incluindo Argentina, Chile, Peru, Bolívia e Equador.");
        respostas.put("QUAL E O PONTO MAIS ALTO DO BRASIL?", "O ponto mais alto do Brasil é o Pico da Neblina, localizado no Amazonas, com 2.995 metros de altura.");
        respostas.put("ONDE FICA O MONTE KILIMANJARO?", "O Monte Kilimanjaro está localizado na Tanzânia, na África, e é a montanha mais alta do continente.");
        respostas.put("QUAL E O PAIS COM MAIS LAGOAS?", "O Canadá é o país com mais lagoas, com mais de dois milhões de lagos naturais.");
        respostas.put("QUAL E O MAIOR GOLFO DO MUNDO?", "O maior golfo do mundo é o Golfo do México, localizado entre os Estados Unidos, México e Cuba.");
        respostas.put("QUAIS SAO OS PAISES DO MERCOSUL?", "Os países-membros do Mercosul são: Argentina, Brasil, Paraguai e Uruguai, com a Bolívia em processo de adesão.");
        respostas.put("QUAL E O PAIS COM MAIOR NÚMERO DE FUSOS HORARIOS?", "A França, incluindo todos os seus territórios ultramarinos, possui o maior número de fusos horários, com um total de 12.");
        respostas.put("QUAL E O MAIOR NUMERO PRIMO MENOR QUE 100?", "O maior número primo menor que 100 é o 97.");
        respostas.put("QUAL E A RAIZ QUADRADA DE 144?", "A raiz quadrada de 144 é 12.");
        respostas.put("QUAL E O NUMERO PI?", "O número Pi é uma constante matemática aproximadamente igual a 3,14159, usada para calcular o perímetro e a área de círculos.");
        respostas.put("QUAL E A FORMULA DA AREA DE UM CIRCULO?", "A fórmula da área de um círculo é A = π * r², onde r é o raio do círculo.");
        respostas.put("QUAL E A FORMULA DA CIRCUNFERENCIA DE UM CIRCULO?", "A fórmula da circunferência de um círculo é C = 2 * π * r, onde r é o raio.");
        respostas.put("O QUE E UM NUMERO PRIMO?", "Um número primo é um número maior que 1 que possui apenas dois divisores: 1 e ele mesmo.");
        respostas.put("QUAL E A FORMULA DO TEOREMA DE PITAGORAS?", "O Teorema de Pitágoras afirma que em um triângulo retângulo, a² + b² = c², onde c é a hipotenusa.");
        respostas.put("QUAL E A FORMULA PARA CALCULAR A MEDIA ARITMETICA?", "A média aritmética é a soma dos valores dividida pelo número de valores.");
        respostas.put("O QUE E UM NUMERO IRRACIONAL?", "Um número irracional é um número que não pode ser expresso como uma fração de dois inteiros, como π e √2.");
        respostas.put("QUAL E O VALOR DE 2 ELEVADO A 10?", "O valor de 2 elevado a 10 é 1024.");
        respostas.put("QUANTOS GRAUS TEM UM TRIANGULO?", "A soma dos ângulos internos de qualquer triângulo é sempre 180 graus.");
        respostas.put("O QUE E UMA PROGRESSAO ARITMETICA?", "Uma Progressão Aritmética (PA) é uma sequência de números em que a diferença entre dois termos consecutivos é constante.");
        respostas.put("O QUE E UMA PROGRESSAO GEOMETRICA?", "Uma Progressão Geométrica (PG) é uma sequência de números em que cada termo é obtido multiplicando o termo anterior por uma constante.");
        respostas.put("QUAL E A FORMULA PARA CALCULAR O VOLUME DE UM CUBO?", "A fórmula do volume de um cubo é V = a³, onde a é a medida do lado do cubo.");
        respostas.put("QUAL E O FATORIAL DE 5?", "O fatorial de 5, representado por 5!, é 120.");
        respostas.put("QUAL E A FORMULA PARA CALCULAR A AREA DE UM TRIANGULO?", "A fórmula da área de um triângulo é A = (base * altura) / 2.");
        respostas.put("O QUE E UMA EQUACAO QUADRATICA?", "Uma equação quadrática é uma equação do tipo ax² + bx + c = 0, onde a, b e c são constantes e a ≠ 0.");
        respostas.put("QUAL E O VALOR DE LOG10 DE 100?", "O valor de log10 de 100 é 2, pois 10² = 100.");
        respostas.put("QUAL E O PRIMEIRO NUMERO PRIMO?", "O primeiro número primo é o 2.");
        respostas.put("QUAL E A FORMULA DA DERIVADA DE X AO QUADRADO?", "A derivada de x² em relação a x é 2x.");
        respostas.put("O QUE E UMA FUNCAO EXPONENCIAL?", "Uma função exponencial é uma função do tipo f(x) = a * b^x, onde a e b são constantes e b > 0.");
        respostas.put("QUAL E A FORMULA PARA CALCULAR A AREA DE UM RETANGULO?", "A área de um retângulo é calculada pela fórmula A = base * altura.");
        respostas.put("QUAL E A FORMULA DO PERIMETRO DE UM QUADRADO?", "O perímetro de um quadrado é calculado como P = 4 * lado.");
        respostas.put("QUAL E O ANGULO DE UMA LINHA RETA?", "O ângulo de uma linha reta é de 180 graus.");
        respostas.put("QUAL E O VALOR DE E?", "O valor de e, também conhecido como a constante de Euler, é aproximadamente 2,718.");
        respostas.put("O QUE E UMA MATRIZ?", "Uma matriz é um arranjo retangular de números em linhas e colunas, usada em várias operações matemáticas.");
        respostas.put("O QUE E UM NUMERO COMPLEXO?", "Um número complexo é um número da forma a + bi, onde a e b são números reais e i é a unidade imaginária, com i² = -1.");
        respostas.put("O QUE E UM ANGULO RETO?", "Um ângulo reto é um ângulo de 90 graus.");
        respostas.put("TCHAU", "Até mais! Volte quando precisar.");
        respostas.put("ATE LOGO", "Até logo! Estarei por aqui se precisar de ajuda.");;
        respostas.put("QUAL E O PAIS MAIS POPULOSO?", "O país mais populoso é a China, seguida pela Índia.");
        respostas.put("QUANDO FOI A PRIMEIRA GUERRA MUNDIAL?", "A Primeira Guerra Mundial começou em 1914 e terminou em 1918.");
        respostas.put("QUEM FOI MICHAEL JACKSON?", "Michael Jackson foi um cantor e dançarino, conhecido como o 'Rei do Pop'.");
        respostas.put("QUEM CANTOU A MUSICA THRILLER?", "A música 'Thriller' foi cantada por Michael Jackson.");
        respostas.put("QUEM E O CANTOR DA MUSICA IMAGINE?", "A música 'Imagine' foi cantada por John Lennon.");
        respostas.put("QUAIS SÃO OS INSTRUMENTOS DE UMA BANDA DE ROCK?", "Guitarra, baixo, bateria e, às vezes, teclado ou piano.");
        respostas.put("QUAL FOI O PRIMEIRO FILME DA DISNEY?", "O primeiro filme da Disney foi 'Branca de Neve e os Sete Anões', lançado em 1937.");
        respostas.put("QUEM E O DIRETOR DE TITANIC?", "O diretor do filme 'Titanic' é James Cameron.");
        respostas.put("QUAL FILME GANHOU MAIS OSCARS?", "O filme que mais ganhou Oscars foi 'Ben-Hur', 'Titanic' e 'O Senhor dos Anéis: O Retorno do Rei', cada um com 11 prêmios.");
        respostas.put("QUEM INTERPRETOU O PERSONAGEM HARRY POTTER?", "O ator Daniel Radcliffe interpretou Harry Potter nos filmes.");
        respostas.put("QUEM E O MELHOR JOGADOR DE FUTEBOL DO MUNDO?", "Essa é uma questão de opinião! Mas alguns dos melhores são Pelé, Maradona, Lionel Messi e Cristiano Ronaldo.");
        respostas.put("QUANTOS JOGADORES TEM UM TIME DE FUTEBOL?", "Um time de futebol tem 11 jogadores em campo.");
        respostas.put("QUANTOS JOGADORES TEM UM TIME DE BASQUETE?", "Um time de basquete tem 5 jogadores em quadra.");
        respostas.put("QUAL E O TIME MAIS VITORIOSO DA NBA?", "O Boston Celtics e o Los Angeles Lakers são os times mais vitoriosos da NBA.");
        respostas.put("QUEM E O JOGADOR MAIS FAMOSO DO BASQUETE?", "Michael Jordan é considerado um dos jogadores de basquete mais famosos e influentes.");
        respostas.put("ONDE FICA A TORRE EIFFEL?", "A Torre Eiffel fica em Paris, na França.");
        respostas.put("ONDE ESTA LOCALIZADA A AMAZÔNIA?", "A Amazônia está localizada na América do Sul, principalmente no Brasil.");
        respostas.put("QUAL E A RAIZ QUADRADA DE 64?", "A raiz quadrada de 64 é 8.");
        respostas.put("QUAL E O VALOR DE PI?", "O valor aproximado de Pi é 3,14159.");
        respostas.put("QUANTO E 10 VEZES 10?", "10 vezes 10 é igual a 100.");
        respostas.put("QUAL E A ESTRELA MAIS PROXIMA DA TERRA?", "A estrela mais próxima da Terra é o Sol.");
        respostas.put("O QUE E A GRAVIDADE?", "A gravidade é uma força que atrai os objetos para o centro da Terra ou de qualquer corpo celeste.");
        respostas.put("QUAL E O PLANETA MAIS QUENTE DO SISTEMA SOLAR?", "O planeta mais quente do Sistema Solar é Vênus.");
        respostas.put("QUAL E O ELEMENTO QUIMICO REPRESENTADO PELO SIMBOLO O?", "O símbolo 'O' representa o Oxigênio.");
        respostas.put("QUANDO FOI A INDEPENDENCIA DO BRASIL?", "A Independência do Brasil foi proclamada em 7 de setembro de 1822.");
        respostas.put("O QUE FOI A REVOLUCAO INDUSTRIAL?", "A Revolução Industrial foi um período de grandes avanços tecnológicos que começou no século XVIII na Inglaterra.");
        respostas.put("QUAL CIVILIZACAO CONSTRUIU AS PIRAMIDES DO EGITO?", "As pirâmides do Egito foram construídas pela civilização egípcia antiga.");
        respostas.put("QUAL E O SEU NOME?", "Sou um assistente virtual e estou aqui para responder suas perguntas!");
        respostas.put("QUAL A SUA IDADE?", "Eu não tenho idade. Sou um programa de computador!");
        respostas.put("VOCE GOSTA DE FUTEBOL?", "Eu gosto de responder perguntas sobre futebol e todos os outros temas!");
        respostas.put("VOCÊ PODE ME AJUDAR?", "Claro! Estou aqui para ajudar com o que você precisar.");
        respostas.put("QUAL A SUA COR FAVORITA?", "Eu não tenho uma cor favorita, mas posso responder sobre qualquer cor que você gostar!");

        // Iniciando o servidor
        try {
            // Configura o servidor para escutar na porta 6789
            ServerSocket socketRecepcao = new ServerSocket(6789);
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                // Aceitando uma conexão de cliente
                Socket socketConexao = socketRecepcao.accept();
                System.out.println("Cliente conectado.");

                // Criando o BufferedReader para receber mensagens do cliente
                BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
                // Criando o DataOutputStream para enviar mensagens ao cliente
                DataOutputStream paraCliente = new DataOutputStream(socketConexao.getOutputStream());

                String fraseCliente;
                while ((fraseCliente = doCliente.readLine()) != null) {
                    // Normalizando a frase do cliente (remover acentos e caracteres especiais)
                    fraseCliente = normalizarTexto(fraseCliente);

                    // Verificando se a frase do cliente existe no mapa de respostas
                    String resposta = respostas.getOrDefault(fraseCliente, "Desculpe, não entendi sua pergunta. Pode reformular?");
                    paraCliente.writeBytes("Bot: " + resposta + "\n");

                    // Se o cliente enviar "SAIR", encerramos a conexão
                    if (fraseCliente.equals(normalizarTexto("SAIR"))) {
                        System.out.println("Cliente desconectado.");
                        break;
                    }
                }

                // Fechando conexão com o cliente
                socketConexao.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para normalizar o texto (remover acentos e caracteres especiais)
    public static String normalizarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return texto.replaceAll("[^\\p{ASCII}]", "").toUpperCase();
    }
}
