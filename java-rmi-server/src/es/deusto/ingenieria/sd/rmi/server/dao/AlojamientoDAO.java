PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    public void insertarAlojamientoBD(String nombre, String descripcion, String direccion) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            AlojamientoDTO alojamiento = new AlojamientoDTO();
            alojamiento.setNombre(nombre);
            alojamiento.setDescripcion(descripcion);
            alojamiento.setDireccion(direccion);

            persistentManager.makePersistent(alojamiento);

            System.out.println("+ Inserted alojamiento into db: " + alojamiento.getNombre);

            transaction.commit();

        } catch (Exception e) {
            System.err.println("DBException");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }