# Veículos Pajeu

## Informações do Projeto
Sistema para Locadora de Veículos - (adaptado de (Heuser, 2009)).

O objetivo do projeto é construir um Sistema para a empresa fictícia de locação de veículos "Veículos Pajeú". A empresa aluga automôveis, camionetas de passageiros e camionetas de carga.

## Informativo
Projeto inicialmente deselvolvido no período de 2018.2 para a disciplina de Projeto de Banco de Dados Relacionais (PBD) - 1ª Versão.

Tendo continuidade no atual período de 2020.1 na disciplina de Arquitetura de Software - 2ª Versão.

Para mais informações consultar a pasta `docs` do projeto.

## Tecnologias utilizadas
- [x] [Java 8](https://www.java.com/)
- [x] [JPA](https://www.oracle.com/java/technologies/persistence-jsp.html)
- [x] [Hibernate](https://hibernate.org/)
- [x] [JavaFX](https://openjfx.io/)
- [x] [PostgreSQL 9.4](https://www.postgresql.org/)

## Padrões de projeto implementados
- [x] Observer
- [x] Facade
- [x] Singleton
- [x] Template Method
- [x] Factory Method
- [x] Abstract Factory
- [x] Iterator
- [x] Lazy Initialization
- [x] Business Object (BO)
- [x] Value Object (VO)
- [x] Data Transfer Object (DAO)
- [x] MVC
- [ ] Memento
- [ ] Decorator
- [x] Adapter
- [x] Synchronized Token
- [x] Proxy

## Localização dos Padrões
- Observer
	- `Util > Ouvinte`
	- `Util > Sujeito`
	- `App > App`
	- `Controle > ControleCarregar`
	
- Facade
	- `Fachada > Fachada`
	
- Singleton
	- `Util > Backup`
	- `Util > CriptografiaUtil`
	- `Util > DateUtil`
	- `Util > LoadBusiness`
	- `Util > MaskFieldUtil`
	- `Util > SynchronizedToken`
	- `Util > TimeUtil`
	- `View > Notificacao`
	- `Fachada > Fachada`
	- `Connection > Connection`
 
- Template Method
	- `Business > Business > validation(entidade);`
 
- Factory Method
	- `Business > Business > createDao()`
 
- Abstract Factory
	- `Connection > Connection`
	- `Connection > IConnectionFactory`
	- `Connection > ConnectionPostgre`
	- `Connection > ConnectionMySql`
 
- Iterator
	- `Enum > Estado`
	- `Util > Sujeito`
	- `Controle > ControleEstatistica`
	- `Util > SQLUtil`
 
- Lazy Initialization
	- `Util > LoadBusiness`
	- `Fachada > Fachada`
 
- Business Object (BO)
	- `Business`
- Value Object (VO)
	- `Entidade`
- Data Transfer Object (DAO)
	- `Dao`
- MVC
	- `View`, `Entidade`, `Controle`
	
- Adapter
	- `Controle > ControleAdapter`
	
- Synchronized Token
	- `Util > SynchronizedToken`
	- `Controle > ControleLogin`
	- `Controle > ControleBusca`
 
- Proxy
	- `Business > BusinessUtilProxy`
