const fastify = require('fastify')({ logger: true })
const path = require('path')

fastify.register(require('fastify-static'), {
  root: __dirname,
  //root: path.join(__dirname, 'public'),
  //prefix: '/public/', // optional: default '/'
})

fastify.get('/hello', async (request, reply) => {
  return { hello: 'world' }
})


const start = async () => {
  try {
    await fastify.listen(3000, '192.168.56.101')
    fastify.log.info(`server listening on ${fastify.server.address().port}`)
  } catch (err) {
    fastify.log.error(err)
    process.exit(1)
  }
}
start()
